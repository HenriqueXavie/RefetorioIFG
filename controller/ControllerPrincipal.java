package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Caixa;
import view.JanelaPrincipal;

public class ControllerPrincipal {
	
	JanelaPrincipal form;
	ControllerPedidoRapido formPedido;
	static Caixa caixa = new Caixa();
	
	
	public ControllerPrincipal() {
		form = new JanelaPrincipal();
		iniciarEventos();
	}
	
	private void iniciarEventos(){
		form.btnCadastrar.addActionListener(new ActionListener() {	
			/**
			 * Chamar tela de cadastro*/
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ControllerCadastro();
			}
		});
		
		form.btnPedidoRapido.addActionListener(new ActionListener() {
			/**
			 * Chamar janela de validação de cliente registrado*/
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ControllerValidacao();
			}
		});
		
		form.btnListaDeClientes.addActionListener(new ActionListener() {
			/**
			 *Chamar janela onde se encontram todos os clientes armazenados no sistema*/
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ControllerGridCliente();
			}
		});
		
		form.btnAvancado.addActionListener(new ActionListener() {	
			/**
			 * Chamar janela onde estão alguns registros e a opção de zerar os registros*/
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ControllerJanelaAvancado();
			}
		});
		
	}
	
}
