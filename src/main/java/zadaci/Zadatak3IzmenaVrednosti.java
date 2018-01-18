package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {
    static Dao<Roba,Integer> robaDao;
    static Dao<Avion,Integer> avionDao;

    public static void main(String[] args){
        ConnectionSource connectionSource = null;
        try {

            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao= DaoManager.createDao(connectionSource, Roba.class);

        List<Roba> PlasticnaRoba=robaDao.queryForEq("opis", "plasticna");
            for (Roba r:PlasticnaRoba) {
                r.setOpis("Drvena");
                robaDao.update(r);

            }



            List<Roba> robe=robaDao.queryForAll();
            for(Roba r:robe)
                System.out.println(r);


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
