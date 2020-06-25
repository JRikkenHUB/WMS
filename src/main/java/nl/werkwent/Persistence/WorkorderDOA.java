package nl.werkwent.Persistence;

import nl.werkwent.DTO.ProductDTO;
import nl.werkwent.DTO.WorkorderDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class WorkorderDOA implements IWorkorderDOA {
    private IConnectionFactory connectionFactory;

    @Override
    public void CreateNewWorkorder(WorkorderDTO workorderDTO){
        try(Connection connection = connectionFactory.getConnection()) {
            UUID klantid = UUID.randomUUID();
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
            //TODO replace with custom error
            e.printStackTrace();
        }
    }
}
