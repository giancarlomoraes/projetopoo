package br.ufg.inf.projetopoo.exceptions;

import javax.swing.JOptionPane;

public class WrongInputException extends NumberFormatException {

	public WrongInputException(String errorMessage) {
		super(errorMessage);
		JOptionPane.showMessageDialog(null, "Aviso!", errorMessage, JOptionPane.WARNING_MESSAGE);
	}
}
