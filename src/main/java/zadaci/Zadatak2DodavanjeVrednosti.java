package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import model.Avion;
import model.Roba;

public class Zadatak2DodavanjeVrednosti {
    static Dao<Roba,Integer> robaDao;
    static Dao<Avion,Integer> avionDao;

    public static void main(String[] args){
        ConnectionSource connectionSource = null;
        try {

            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);
            /*Instanciranje odgovarajucih Dao objekata koristeci pomocnu metodu
              createDao klase DaoManager
            */

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao= DaoManager.createDao(connectionSource, Roba.class);

            /*Brisanje vrednosti iz tabela koje mora biti
              u navedenom redosledu - roba pa avion      */
            TableUtils.clearTable(connectionSource,Roba.class);
            TableUtils.clearTable(connectionSource,Avion.class);

            Avion a1=new Avion("Avion1", 34);
            avionDao.create(a1);

            Avion a2=new Avion("Avion2", 21);
            avionDao.create(a2);


            Roba r1=new Roba("Patike", "Duboke patike", 1, a1);
            robaDao.create(r1);

            Roba r2=new Roba("Kosulja", "Na duge rukave", 0.4, a1);
            robaDao.create(r2);

            Roba r3=new Roba("Voda", "Gazirana", 1.4, a1);
            robaDao.create(r3);

            Roba r4=new Roba("Plocice", "drvene", 3.4, a2);
            robaDao.create(r4);

            Roba r5=new Roba("Stolica", "plasticna", 2.4, a2);
            robaDao.create(r5);



            /*Resenje 2.3.2
               Izmena vrednosti za atribut oznaka u
               "RacunPrvi" i cuvanje izmene pozivom metode
                update i prosledjivanjem izmenjenog objekta
               */
          /*  Racun zaIzmenu=racunDao.queryForId(r1.getId());
            zaIzmenu.setOznaka("RacunPrvi");
            racunDao.update(zaIzmenu);
            racunDao.update(zaIzmenu);*/

            /*Opcioni prikaz svih racuna da bi videli da
            su vrednosti izmenjene
            */
            List<Avion> avioni=avionDao.queryForAll();

            for(Avion a:avioni)
                System.out.println(a);


            /*
               Resenje 2.3.3
               Instanciranje novog objekta klase Artikal
               upis te vrednosti u bazu pozivom metode
               create i prosledjivanjem objekta
             */
         /*   Artikal a5=new Artikal("Voda","Flasa vode od 1,5L", 70);
            artikalDao.create(a5);*/

            /*
             Prikaz svih artikala da bi videli da
              je objekat upisan. Metoda queryForAll vraca
              sve vrednosti iz odgovarajuce tabele za koju je
              napravljen Dao objekat.
             */
            List<Roba> robe=robaDao.queryForAll();
                 for(Roba r:robe)
                System.out.println(r);

            /*
              Koristeci metodu queryForId pronalazimo objekat
              koji za atribut id ima istu vrednost kao i poslednji
              kreirani objekat i brisanje vrednosti pozivom metode
              delete i prosledjivanjem pronadjenog objekta
             */
           /* Artikal zaBrisanje=artikalDao.queryForId(a5.getId());
            artikalDao.delete(zaBrisanje);*/

            /*
              Prikaz svih artikala da bi videli da
               je objekat obrisan
             */
          /*  artikli=artikalDao.queryForAll();
            System.out.println("Prikaz svih artikala " +
                    "da vidimo da je obrisan artikal sa " +
                    "nazivom Voda");
            for(Artikal a:artikli)
                System.out.println(a);*/

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
