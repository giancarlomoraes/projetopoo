package br.ufg.inf.projetopoo.exceptions;

import javax.swing.JOptionPane;

public class WrongInputException extends Exception {

	public WrongInputException(String errorMessage) {
		super(errorMessage);
		JOptionPane.showMessageDialog(null, errorMessage, "Aviso!", JOptionPane.WARNING_MESSAGE);
	}
}
