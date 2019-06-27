package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Pizzas;
import model.Refrigerante;
import model.Suco;
import view.FormPedidoRapido;

public class ControllerPedidoRapido {
	
	public FormPedidoRapido form;
	Cliente c = new Cliente();
	DecimalFormat df = new DecimalFormat();
	
	public ControllerPedidoRapido() {
		form = new FormPedidoRapido();
		iniciarEventos();
		carregarTela();
	}
	
	private Locale localeBrasil = new Locale("pt", "BR");
	private NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(localeBrasil);
	double precoSoma;
	
	// Método utilitário
	public double parseValor(String str){
	    Number valor = 0;
	    try {
	        valor = formatoMonetario.parse(str);
	    } catch (ParseException e) {
	    	e.getMessage();
	    }
	    return valor.doubleValue();
	}

	
	private void iniciarEventos(){ //Apresentar valor do item	
		
		form.cmbPizza.addItemListener(new ItemListener() { //Carregar label com o preço do item selecionado no enum
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Pizzas p = (Pizzas) e.getItem();
					form.lblPrecoPizza.setText(formatoMonetario.format(p.getValue()));
				} else {
					form.lblPrecoPizza.setText(formatoMonetario.format(0));
				}
			}
		});
		
		form.cmbSuco.addItemListener(new ItemListener() { //Carregar label com o preço do item selecionado no enum
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Suco s = (Suco) e.getItem();
					form.lblPrecoSuco.setText(formatoMonetario.format(s.getValue()));
				}else{
					form.lblPrecoSuco.setText(formatoMonetario.format(0));				}
			}
		});
		
		form.cmbRefri.addItemListener(new ItemListener() { //Carregar label com o preço do item selecionado no enum
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Refrigerante r = (Refrigerante) e.getItem();
					form.lblPrecoRefri.setText(formatoMonetario.format(r.getValue()));
				}else{
					form.lblPrecoRefri.setText(formatoMonetario.format(0));
				}
			}
		});
		
		form.btnCalcular.addActionListener(new ActionListener() { //Apresentar soma dos produtos
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				form.precoPizza = parseValor(form.lblPrecoPizza.getText());
				form.precoSuco = parseValor(form.lblPrecoSuco.getText());
				form.precoRefri = parseValor(form.lblPrecoRefri.getText());
				precoSoma = form.precoPizza 
						+ form.precoSuco + form.precoRefri; 
				
				form.txtPrecoTotal.setText(formatoMonetario.format(precoSoma));
				
				if(form.chkVip.isSelected()){ //Desconto de 15% para clientes vips
					double desconto = (precoSoma)*0.15;
					precoSoma -= desconto;
					form.txtPrecoTotal.setText(formatoMonetario.format(precoSoma));
				}
				form.btnConfirmar.setEnabled(true);
			}
		});
		
		form.btnConfirmar.addActionListener(new ActionListener() { //Confirmar pedido
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerPrincipal.caixa.setPrecoTotal(ControllerPrincipal.caixa.getPrecoTotal() +
						precoSoma);
				form.setVisible(false);
				JOptionPane.showMessageDialog(null ,"Pedido realizado com sucesso !");
			}
		});
		
	}
	
	private void carregarTela(){ //Carregar JComboBox com Enums
		
		for(Pizzas pizza : Pizzas.values()){
			form.cmbPizza.addItem(pizza);
		}
		for(Suco suco : Suco.values()){
			form.cmbSuco.addItem(suco);
		}
		for(Refrigerante refri : Refrigerante.values()){
			form.cmbRefri.addItem(refri);
		}
	}
	
}
