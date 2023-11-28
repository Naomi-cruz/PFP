/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Database.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Controlador.ControladorGruposAdministrador;
import com.toedter.calendar.JDateChooser;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author ERIKA GARCIA
 */
public class ControladorAltaAlumno {
    
    String nombAlum;
    String apellidopatalum;
    String apellidomatalum;
    int semestrealum;
    String fechanacimientoalum; 
    String curp;
    int numcontrol;

    
    ControladorGruposAdministrador cga = new ControladorGruposAdministrador();
          
    public String getNombAlum() {
        return nombAlum;
    }

    public void setNombAlum(String nombAlum) {
        this.nombAlum = nombAlum;
    }

    public String getApellidopatalum() {
        return apellidopatalum;
    }

    public void setApellidopatalum(String apellidopatalum) {
        this.apellidopatalum = apellidopatalum;
    }

    public String getApellidomatalum() {
        return apellidomatalum;
    }

    public void setApellidomatalum(String apellidomatalum) {
        this.apellidomatalum = apellidomatalum;
    }

    public int getSemestrealum() {
        return semestrealum;
    }

    public void setSemestrealum(int semestrealum) {
        this.semestrealum = semestrealum;
    }

    public String getFechanacimientoalum() {
        return fechanacimientoalum;
    }

    public void setFechanacimientoalum(String fechanacimientoalum) {
        this.fechanacimientoalum = fechanacimientoalum;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getNumcontrol() {
        return numcontrol;
    }

    public void setNumcontrol(int numcontrol) {
        this.numcontrol = numcontrol;
    }

    public void alumno(JTable tablaprueba){
        ControladorGruposAdministrador cad = new ControladorGruposAdministrador();
        
        //String na = " ";
        //cad.nomAlum;

        Conexion con = new Conexion(); //--
        DefaultTableModel modelP = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTable = new TableRowSorter<TableModel>(modelP);
         tablaprueba.setRowSorter(OrdenarTable);
         
        String sql2 = "";
        modelP.addColumn("Nombre");
        modelP.addColumn("AP");
        modelP.addColumn("AM");
        modelP.addColumn("Semes");
        modelP.addColumn("Nac");
        modelP.addColumn("Curp");
        modelP.addColumn("Ncontrol");
        
        
         sql2 = "SELECT nombAlum, apePatAlum, apeMatAlum, semestreAlum, fechaNacAlum, curpAlum, nControlAlum FROM alumno;";
        //sql2 = "SELECT nombAlum, apePatAlum, apeMatAlum, semestreAlum, fechaNacAlum, curpAlum, nControlAlum WHERE nombAlum = FROM alumno;";
        Statement st2; 
        String[] cabecera2 = new String[7];
        
        
        try {
            //st = con.getConnection().createStatement();
            st2 = con.conecta().createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            
            while(rs.next()){
                cabecera2[0] = rs.getString(1);
                cabecera2[1] = rs.getString(2);
                cabecera2[2] = rs.getString(3);
                cabecera2[3] = rs.getString(4);
                cabecera2[4] = rs.getString(5);
                cabecera2[5] = rs.getString(6);
                cabecera2[6] = rs.getString(7);
                
                modelP.addRow(cabecera2); 
            }
            tablaprueba.setModel(modelP);
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar registros" + e.toString());     
        
        }
    }
    
     public void seleccionarAlumno(JTable tablaprueba, JTextField ptxtnombre, JTextField ptxtapellidop, JTextField ptxtapellidom, JTextField ptxtsemestre, JTextField ptxtfecha, JTextField ptxtcurp, JTextField ptxtncontrol){
        
        try {
            int recorre = tablaprueba.getSelectedRow();
            
            if (recorre >= 0) {
                
                ptxtnombre.setText(tablaprueba.getValueAt(recorre, 0).toString());
                ptxtapellidop.setText(tablaprueba.getValueAt(recorre, 1).toString());
                ptxtapellidom.setText(tablaprueba.getValueAt(recorre, 2).toString());
                ptxtsemestre.setText(tablaprueba.getValueAt(recorre, 3).toString());
                ptxtfecha.setText(tablaprueba.getValueAt(recorre, 4).toString());
                ptxtcurp.setText(tablaprueba.getValueAt(recorre, 5).toString());
                ptxtncontrol.setText(tablaprueba.getValueAt(recorre, 6).toString());
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al seleccionar :"+e.toString());
        }
    }
     
     
   
     public void insertarAlumno(JTextField ptxtnombre, JTextField ptxtapellidop, JTextField ptxtapellidom, JTextField ptxtsemestre, JTextField ptxtfecha, JTextField ptxtcurp, JTextField ptxtncontrol){
     
        setNombAlum(ptxtnombre.getText());
        setApellidopatalum(ptxtapellidop.getText());   
        setApellidomatalum(ptxtapellidom.getText());
        setSemestrealum(Integer.parseInt(ptxtsemestre.getText()));
        setFechanacimientoalum(ptxtfecha.getText());   
        setCurp(ptxtcurp.getText());
        setNumcontrol(Integer.parseInt(ptxtncontrol.getText())); 
        Conexion con = new Conexion();
        String consulta ="INSERT INTO alumno (nombAlum, apePatAlum, apeMatAlum, semestreAlum, fechaNacAlum, curpAlum, nControlAlum) VALUES (?,?,?,?,?,?,?);";
        
        try {
           CallableStatement cs = con.conecta().prepareCall(consulta);
           cs.setString(1, getNombAlum());
           cs.setString(2, getApellidopatalum());       
           cs.setString(3, getApellidomatalum());   
           cs.setInt(4, getSemestrealum());   
           cs.setString(5, getFechanacimientoalum());   
           cs.setString(6, getCurp());   
           cs.setInt(7, getNumcontrol());   
          
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "El alumno se agregó exitosamente");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar: "+e.toString());
        } 
    }
     
     public void eliminarAlumno(JTextField ptxtncontrol){
         setNumcontrol(Integer.parseInt(ptxtncontrol.getText()));
         
         
        Conexion con = new Conexion();
        String consulta = "DELETE FROM alumno WHERE alumno.nControlAlum = ?;";
        
        try {
            CallableStatement cs = con.conecta().prepareCall(consulta);
            cs.setInt(1, getNumcontrol());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se eliminó, error: "+e.toString());
        }
    }
}
