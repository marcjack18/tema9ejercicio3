package com.series.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.series.dao.DAOserie;
import com.series.model.Serie;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	private JFrame frmCrudSeries;
	private JTable table;
	private JTextField textNombre;
	private JTextField textTemporadas;
	private JTextField textCapitulos;
	private JTextField textId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmCrudSeries.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		DAOserie ds=new DAOserie();
		List<Serie> ls=null;
		
		frmCrudSeries = new JFrame();
		frmCrudSeries.setTitle("CRUD series");
		frmCrudSeries.setBounds(100, 100, 665, 539);
		frmCrudSeries.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudSeries.getContentPane().setLayout(null);
				
		DefaultTableModel model = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		model.addColumn("ID");
		model.addColumn("Título");
		model.addColumn("Nº temporadas");
		model.addColumn("Total episodios");
		
		ls=ds.selectAllSeries();
		
		for (int i=0;i<ls.size();i++) {
			Object[] row=new Object[4];
			row[0]=ls.get(i).getId();
			row[1]=ls.get(i).getNombre();
			row[2]=ls.get(i).getTemporadas();
			row[3]=ls.get(i).getCapitulos();
			model.addRow(row);
		}
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index=table.getSelectedRow();
				TableModel model=table.getModel();
				
				textId.setText(model.getValueAt(index, 0).toString());
				textNombre.setText(model.getValueAt(index, 1).toString());
				textTemporadas.setText(model.getValueAt(index, 2).toString());
				textCapitulos.setText(model.getValueAt(index, 3).toString());
			}	
		});	
		
		JLabel lblNewLabel = new JLabel("Serie:");
		lblNewLabel.setBounds(40, 325, 46, 14);
		frmCrudSeries.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Temporadas:");
		lblNewLabel_1.setBounds(40, 363, 91, 14);
		frmCrudSeries.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cap\u00EDtulos:");
		lblNewLabel_2.setBounds(40, 401, 80, 14);
		frmCrudSeries.getContentPane().add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setBounds(141, 325, 259, 20);
		frmCrudSeries.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textTemporadas = new JTextField();
		textTemporadas.setBounds(141, 363, 86, 20);
		frmCrudSeries.getContentPane().add(textTemporadas);
		textTemporadas.setColumns(10);
		
		textCapitulos = new JTextField();
		textCapitulos.setBounds(141, 401, 86, 20);
		frmCrudSeries.getContentPane().add(textCapitulos);
		textCapitulos.setColumns(10);
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(37, 25, 567, 238);
		frmCrudSeries.getContentPane().add(scrollPane);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Serie> ls=null;
				
				model.setRowCount(0);				
				ls=ds.selectAllSeries();				
				for (int i=0;i<ls.size();i++) {
					Object[] row=new Object[4];
					row[0]=ls.get(i).getId();
					row[1]=ls.get(i).getNombre();
					row[2]=ls.get(i).getTemporadas();
					row[3]=ls.get(i).getCapitulos();
					model.addRow(row);
				}
			}
		});
		btnMostrar.setBounds(479, 331, 89, 23);
		frmCrudSeries.getContentPane().add(btnMostrar);
		btnMostrar.setVisible(false);
			
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom;
				int ntemp;
				int ncaps;
				
				nom=textNombre.getText();
				ntemp=Integer.parseInt(textTemporadas.getText());
				ncaps=Integer.parseInt(textCapitulos.getText());
				
				Serie s=new Serie(nom,ntemp,ncaps);
				ds.insertSerie(s);
				btnMostrar.doClick();
			}
		});
		btnGuardar.setBounds(82, 450, 107, 23);
		frmCrudSeries.getContentPane().add(btnGuardar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom;
				int ntemp;
				int ncaps;
				int id;
				
				id=Integer.parseInt(textId.getText());
				Serie s=ds.selectSerieById(id);
				
				nom=textNombre.getText();
				ntemp=Integer.parseInt(textTemporadas.getText());
				ncaps=Integer.parseInt(textCapitulos.getText());
				
				s.setNombre(nom);
				s.setTemporadas(ntemp);
				s.setCapitulos(ncaps);
				
				ds.updateSerie(s);
				btnMostrar.doClick();
			}
		});
		btnActualizar.setBounds(271, 450, 107, 23);
		frmCrudSeries.getContentPane().add(btnActualizar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				
				id=Integer.parseInt(textId.getText());		
				ds.deleteSerie(id);
				btnMostrar.doClick();
				textId.setText("");
				textNombre.setText("");
				textTemporadas.setText("");
				textCapitulos.setText("");
			}
		});
		btnBorrar.setBounds(460, 450, 107, 23);
		frmCrudSeries.getContentPane().add(btnBorrar);
		
		JLabel lblNewLabel_3 = new JLabel("Id:");
		lblNewLabel_3.setBounds(40, 288, 46, 14);
		frmCrudSeries.getContentPane().add(lblNewLabel_3);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(141, 288, 86, 20);
		frmCrudSeries.getContentPane().add(textId);
		textId.setColumns(10);
		
		
	}
}
