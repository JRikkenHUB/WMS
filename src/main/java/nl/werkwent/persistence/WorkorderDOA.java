package nl.werkwent.persistence;

import com.mysql.cj.protocol.Resultset;
import nl.werkwent.service.dto.CustomerDTO;
import nl.werkwent.service.dto.ProductDTO;
import nl.werkwent.service.dto.WorkorderDTO;
import nl.werkwent.service.exceptions.DTOConversionException;
import nl.werkwent.service.exceptions.databaseRequestFailedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkorderDOA implements IWorkorderDOA {
    private IConnectionFactory connectionFactory;

    //TODO add descriptions
    @Override
    public void CreateNewWorkorder(WorkorderDTO workorderDTO){
        try(Connection connection = connectionFactory.getConnection()) {
            UUID klantid = UUID.randomUUID();

            //TODO check if customer exists in different function;
            PreparedStatement customerpreparedStatement = connection.prepareStatement("INSERT INTO `customer`(`CUSTOMERID`, `NAME`, `ADRES`, `POSTALCODE`, `LOCATIONNAME`, `SHIPPINGADRES`, `SHIPPINGPOSTALCODE`, `SHIPPINGLOCATIONNAME`) " +
                    "VALUES (?,?,?,?,?,?,?,?) ");

            customerpreparedStatement.setString(1, String.valueOf(klantid));
            customerpreparedStatement.setString(2, workorderDTO.getKlant().getNaam());
            customerpreparedStatement.setString(3, workorderDTO.getKlant().getAdres());
            customerpreparedStatement.setString(4, workorderDTO.getKlant().getPostcode());
            customerpreparedStatement.setString(5, workorderDTO.getKlant().getPlaatsnaam());
            customerpreparedStatement.setString(6, workorderDTO.getKlant().getOntvangstAdres());
            customerpreparedStatement.setString(7, workorderDTO.getKlant().getOntvangstPostcode());
            customerpreparedStatement.setString(8, workorderDTO.getKlant().getOntvangstPlaatsnaam());
            customerpreparedStatement.executeQuery();

            PreparedStatement productpreparedstatement = connection.prepareStatement("INSERT INTO `picklist`(`ARTIKELNUMBER`, `WORKORDERNUMBER`, `DESCRIPTION`, `UNIT`, `PICKLISTAMOUNT`) " +
                    "VALUES (?,?,?,?,?)");
            for (ProductDTO product : workorderDTO.getProducten()){
                 productpreparedstatement.setInt(1, product.getArtikelNummer());
                 productpreparedstatement.setString(2, workorderDTO.getWorkorderNummer());
                 productpreparedstatement.setString(3, product.getOmschrijving());
                 productpreparedstatement.setString(4, product.getEenheid());
                 productpreparedstatement.setInt(5, product.getAantal());
                 productpreparedstatement.executeQuery();
            }

            PreparedStatement workorderpreparedstatement = connection.prepareStatement("INSERT INTO `workorder`(`WORKORDERNUMBER`, `ORDERNUMBER`, `CUSTOMERID`, `RECIEVINGDATE`, `DELIVERYDATE`, `SENDDATE`, `PRODUCTIONSTART`, `PRODUCTIONEND`) " +
                    "VALUES (?,?,?,?,?,?,?,?)");

            workorderpreparedstatement.setString(1, workorderDTO.getWorkorderNummer());
            workorderpreparedstatement.setString(2, workorderDTO.getBestelNummer());
            workorderpreparedstatement.setString(3, String.valueOf(klantid));
            workorderpreparedstatement.setDate(4, (Date) workorderDTO.getOntvangstDatum());
            workorderpreparedstatement.setDate(5, (Date) workorderDTO.getLeverDatum());
            workorderpreparedstatement.setDate(6, (Date) workorderDTO.getVerzendDatum());
            workorderpreparedstatement.setDate(7, (Date) workorderDTO.getProductieStart());
            workorderpreparedstatement.setDate(8, (Date) workorderDTO.getProductieEinde());

            workorderpreparedstatement.executeQuery();


        } catch (SQLException e) {
            //TODO rollback on error
            throw new databaseRequestFailedException();
        }
    }

    @Override
    public WorkorderDTO getWorkorder(String id) {
        ResultSet workorderInfo = null;
        ResultSet customerInfo = null;
        ResultSet productInfo = null;

        try(Connection connection = connectionFactory.getConnection()){
            //get workorder info
            PreparedStatement selectWorkorder = connection.prepareStatement("SELECT * FROM `workorder` WHERE WORKORDERNUMBER = ?");
            selectWorkorder.setString(1, id);

            workorderInfo = selectWorkorder.executeQuery();
            workorderInfo.next();

            //get klantinfo
            PreparedStatement selectKlant = connection.prepareStatement("SELECT * FROM `customer` WHERE `CUSTOMERID` = ?");
            selectKlant.setString(1, workorderInfo.getString("CUSTOMERID"));

            customerInfo = selectKlant.executeQuery();
            customerInfo.next();

            //get product info
            PreparedStatement selectProducts = connection.prepareStatement("SELECT * FROM `picklist` WHERE `WORKORDERNUMBER` = ?");
            selectProducts.setString(1, workorderInfo.getString("WORKORDERNUMBER"));

            productInfo = selectProducts.executeQuery();
            productInfo.next();

        } catch (SQLException e) {
            throw new databaseRequestFailedException();
        }

        return workorderDTOFromResultSet(workorderInfo, customerInfo, productInfo);

    }

    public WorkorderDTO workorderDTOFromResultSet(ResultSet workorderInfo, ResultSet customerInfo, ResultSet productInfo){
        WorkorderDTO workorderDTO = new WorkorderDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        List<ProductDTO> products = new ArrayList<>();

        try{
            //set all properties from workorderDTO
            workorderDTO.setWorkorderNummer(workorderInfo.getString("WORKORDERNUMBER"));
            workorderDTO.setBestelNummer(workorderInfo.getString("ORDERNUMBER"));
            workorderDTO.setOntvangstDatum(workorderInfo.getDate("RECIEVINGDATE"));
            workorderDTO.setLeverDatum(workorderInfo.getDate("DELIVERYDATE"));
            workorderDTO.setVerzendDatum(workorderInfo.getDate("SENDDATE"));
            workorderDTO.setProductieStart(workorderInfo.getDate("PRODUCTIONSTART"));
            workorderDTO.setProductieEinde(workorderInfo.getDate("PRODUCTIONEND"));

            //set all properties for customer
            customerDTO.setNaam(customerInfo.getString("NAME"));
            customerDTO.setAdres(customerInfo.getString("ADRES"));
            customerDTO.setPostcode(customerInfo.getString("POSTALCODE"));
            customerDTO.setPlaatsnaam(customerInfo.getString("LOCATIONNAME"));
            customerDTO.setOntvangstAdres(customerInfo.getString("SHIPPINGADRES"));
            customerDTO.setOntvangstPostcode(customerInfo.getString("SHIPPINGPOSTALCODE"));
            customerDTO.setOntvangstPlaatsnaam(customerInfo.getString("SHIPPINGLOCATIONNAME"));

            //asign customer to dto
            workorderDTO.setKlant(customerDTO);

            //create dto's for picklist products
            while (productInfo.next()){
                products.add(new ProductDTO(
                        productInfo.getInt("ARTIKELNUMBER"),
                        productInfo.getString("DESCRIPTION"),
                        productInfo.getString("UNIT"),
                        productInfo.getInt("PICKLISTAMOUNT")
                ));
            }

            //asign products to workorder
            workorderDTO.setProducten(products);

        } catch (Exception e) {
            throw new DTOConversionException();
        }
        return workorderDTO;
    }
}
