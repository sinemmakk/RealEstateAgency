import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import javax.annotation.Resource;
 import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
 import javax.sql.DataSource;
 import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean ( name="kullaniciBean" )
@SessionScoped
public class Kullanici {
    //user.xhtml
    private String girilenEmail;
    private String girilenSifre;
    private String kayitliEmail;
    private String kayitliSifre;
    DataSource dataSource;
    Connection conn;
    
    //createuser.html
    private String girilenAd;
    private String girilenSoyad;
    private String girilenMail;
    private String girilenSifre1;
    private String girilenSifre2;
    
    private String girilenEmail1;
    private String girilenEmail2;
    
    public Kullanici(){
        try {
            InitialContext context = new InitialContext();
            dataSource =(DataSource) context.lookup("jdbc/emlak");
            conn = dataSource.getConnection();
        } 
        catch (NamingException e) {
            e.printStackTrace();
	}
        catch (SQLException se){
            System.out.println(se);
        }
    }
    
    //user
    public String getGirilenEmail(){
        return girilenEmail;
    }
    public String getGirilenSifre(){
        return girilenSifre;
    }
    public String getKayitliEmail(){
        return kayitliEmail;
    }
    public String getKayitliSifre(){
        return kayitliSifre;
    }
    
    public void setGirilenSifre(String girilenSifre){
        this.girilenSifre=girilenSifre;
    }
    public void setKayitliSifre(String kayitliSifre){
        this.kayitliSifre=kayitliSifre;
    }
    public void setGirilenEmail(String girilenEmail){
        this.girilenEmail=girilenEmail;
    }
    public void setKayitliEmail(String kayitliEmail){
        this.kayitliEmail=kayitliEmail;
    }
    
    //create user
    public String getGirilenMail(){
        return girilenMail;
    }
    public String getGirilenAd(){
        return girilenAd;
    }
    public String getGirilenSoyad(){
        return girilenSoyad;
    }
    public String getGirilenSifre1(){
        return girilenSifre1;
    }
    public String getGirilenSifre2(){
        return girilenSifre2;
    }
    public void setGirilenMail(String girilenMail){
        this.girilenMail=girilenMail;
    }
    public void setGirilenAd(String girilenAd){
        this.girilenAd=girilenAd;
    }
    public void setGirilenSoyad(String girilenSoyad){
        this.girilenSoyad=girilenSoyad;
    }
    public void setGirilenSifre1(String girilenSifre1){
        this.girilenSifre1=girilenSifre1;
    }
    public void setGirilenSifre2(String girilenSifre2){
        this.girilenSifre2=girilenSifre2;
    }
    
    public String getGirilenEmail1(){
        return girilenEmail1;
    }
    public String getGirilenEmail2(){
        return girilenEmail2;
    }
    public void setGirilenEmail1(String girilenEmail1){
        this.girilenEmail1=girilenEmail1;
    }
    public void setGirilenEmail2(String girilenEmail2){
        this.girilenEmail2=girilenEmail2;
    }
    
    //user login
    public void login()throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );
        if ( conn == null)
            throw new SQLException( "Unable to connect to DataSource" );
        try{
            String sql="SELECT SIFRE from ADMIN1.KULLANICI WHERE EMAIL='"+girilenEmail+"';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.setString(1, kayitliSifre);
            if(girilenSifre.equals(kayitliSifre)){
                System.out.println("User logged in successfully! ");
            }
        }
        finally{
            conn.close(); 
        }
    }
    
    //createuser sign in
    public void signin()throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );
        if ( conn == null)
            throw new SQLException( "Unable to connect to DataSource" );
        try{
            if(girilenSifre1.equals(girilenSifre2)){
            String sql="INSERT INTO ADMIN1.KULLANICI (AD,SOYAD,EMAIL,SIFRE) VALUES ('"+girilenAd+"','"+girilenSoyad+"','"+girilenMail+"','"+girilenSifre1+"');";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("User signed in successfully! ");
            }
            else{
                System.out.println("Password1 and Password2 doesn't match.");
            }
        }
        finally{
            conn.close(); 
        }
    }
    
    public void update()throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );
        if ( conn == null)
            throw new SQLException( "Unable to connect to DataSource" );
        try{
            String sql="UPDATE ADMIN1.KULLANICI SET AD='"+girilenAd+"',SOYAD='"+girilenSoyad+"',EMAIL='"+girilenEmail2+"',SIFRE='"+girilenSifre2+"' WHERE EMAIL='"+girilenEmail1+"';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("User updated in successfully! ");
        }
        finally{
            conn.close(); 
        }
    }
}
