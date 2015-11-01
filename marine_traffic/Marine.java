/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marine_traffic;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author IIIpaM
 */


public class Marine
{
    public int id;//database table id    
    String name;
    int mmsi;
    String flag;
    int type;
    int length;
    ArrayList<Track> tracks;
    
    public class Track
    {
        float lat;
        float lon;
        int speed;
        int course;// 0-359;
        int age;
        public Date comeDate;  //
        public Date trueDate; // comeDate-age
        public Date accDate; // calculated date
        //public int unixTime; какой именно?
        public Track(float lat, float lon, int speed, int course, int age, Date date)
        {
            this.lat=lat;
            this.lon=lon;
            this.speed=speed;
            this.course=course;
            this.age=age;
            this.comeDate=date;   
            this.trueDate=new Date(comeDate.getTime()-age*60_000);
        }
        
        @Override
        public String toString()
        {
            return "    "+lat+" "+lon+" "+speed+" "+course+" "+trueDate+" "+"\r\n";
        }

        @Override
        public boolean equals(Object o)
        {
            return this.trueDate.equals(((Track)o).trueDate);
        }
    }
    
    public Marine(int id, String n, int m, String f, int t, int l)
    {
        this.id=id;
        tracks=new ArrayList<>();
        name=n;
        mmsi=m;
        flag=f;
        type=t;
        length=l;       
    }
    
    @Override
    public String toString()
    {
        String marineInfo=id+" "+name+" "+mmsi+" "+flag+" "+type+" "+length+"\r\n";
        String tracksInfo="";
        int size = tracks.size();
        
        for (int i=0;i<size;i++)
        {
            tracksInfo=tracksInfo+tracks.get(i);
        }
        
        
        return marineInfo+tracksInfo;
    }
    
    
}
