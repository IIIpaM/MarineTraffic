/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marine_traffic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import marine_traffic.Marine.Track;

/**
 *
 * @author IIIpaM
 */
public class db
{
    private static db instance;
    
    private static final String url = "jdbc:mysql://localhost:3306/marines";
    private static final String user = "root";
    private static final String password = "";
    
    private static Connection con;
    
    public static ArrayList<Marine> getMarines()
    {
        try
        {
            ArrayList<Marine> marines=new ArrayList<>();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select id_marine,mmsi,name,flag,type,length from marine");
            while(rs.next())
            {
                Marine m=new Marine(rs.getInt("id_marine"),rs.getString("name"),rs.getInt("mmsi"),rs.getString("flag"),rs.getInt("type"),rs.getInt("length"));
                marines.add(m);
            }
            int size=marines.size();
            PreparedStatement ps=con.prepareStatement("select lat,lon,speed,course,age,date_add from track where id_marine=?");
            for (int i=0;i<size;i++)
            {
                ps.setInt(1, marines.get(i).id);
                rs=ps.executeQuery();
                while (rs.next())
                {
                    Track t=marines.get(i).new Track(rs.getFloat("lat"),rs.getFloat("lon"),rs.getInt("speed"),rs.getInt("course"),rs.getInt("age"),rs.getDate("date_add"));
                    if (!marines.get(i).tracks.contains(t))
                    {
                        marines.get(i).tracks.add(t);
                    }
                }
            }
            
            
            return marines;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("error getting marines");
            return null;
        }
    }
    

    private db()
            {
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver"); //driver init    
                    con = DriverManager.getConnection(url, user, password);
                } 
                catch (ClassNotFoundException ex)
                {
                   System.out.println(ex);
                   System.out.println("Error getting db driver");
                }
                catch (SQLException sqlEx) 
                {
                    System.out.println(sqlEx.getMessage());
                    System.out.println("Error connecting to database");
                }      
            }
    
    public static db getInstance()
    {
        if (instance==null)
        {

            instance=new db();
        }
        return instance;
    }
}
