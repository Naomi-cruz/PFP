/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Database.Conexion;
import Database.DBConnection;
import Views.AltaAlumnos_Alumno;
import Views.Horario_Administrador;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ERIKA GARCIA
 */
public class ControladorGruposAdministrador {

    public String getNomAlum() {
        return nomAlum;
    }

    public void setNomAlum(String nomAlum) {
        this.nomAlum = nomAlum;
    }
    String nomAlum; 
   
    
    
    //------------------------TABLA AUXILIAR
    /*public void alumno(){
        Conexion con = new Conexion(); //--
        DefaultTableModel modelP = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTable = new TableRowSorter<TableModel>(modelP);
        
        String sql2 = "";
        modelP.addColumn("Nombre");
        modelP.addColumn("AP");
        modelP.addColumn("AM");
          modelP.addColumn("Semes");
        modelP.addColumn("Nac");
        modelP.addColumn("Curp");
        modelP.addColumn("Ncontrol");
        
        String sql2 = "SELECT nombAlum, apePatAlum, apeMatAlum, semestreAlum, fechaNacAlum, nControlAlum FROM alumno;";
        Statement st2; 
         try {
            //st = con.getConnection().createStatement();
            st2 = con.conecta().createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar registros" + e.toString());    
    }
    */
    
    //------------------------
    
     public void mostrarGrupoAlumno(JTable tablaga){
        //DBConnection con = new DBConnection();
        Conexion con = new Conexion(); //--
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTable = new TableRowSorter<TableModel>(model);
        tablaga.setRowSorter(OrdenarTable);
        
        String sql = "";
        model.addColumn("Materia");
        model.addColumn("Grupo");
        model.addColumn("Alumnos inscritos");
        model.addColumn("Lista");
        tablaga.setModel(model);
        
       sql = "SELECT concat(nombAlum,'  ',apePatAlum,'  ',apeMatAlum) FROM alumno; ";
      // sql = "SELECT nombAlum FROM alumno; ";
        String[] cabecera = new String[4];
        Statement st;
      
       
        
        try {
            //st = con.getConnection().createStatement();
            st = con.conecta().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                cabecera[2] = rs.getString(1);
                model.addRow(cabecera); 
            }
            tablaga.setModel(model);
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar registros" + e.toString());     
        
    }
     }
    

    public void seleccionarGrupoAlumno(JTable tablaga){
        
        try {
            int recorre = tablaga.getSelectedRow();
            
            if (recorre >= 0) {
                AltaAlumnos_Alumno Frame = new AltaAlumnos_Alumno();
                Frame.setVisible(true);
                //Frame.dispose();
                
                //Frame.txtnombre.setText((String) tablaga.getValueAt(recorre, 2));
               // paraid.setText(paratabla.getValueAt(recorre, 0).toString());
               
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al seleccionar :"+e.toString());
        }
    }    
}
