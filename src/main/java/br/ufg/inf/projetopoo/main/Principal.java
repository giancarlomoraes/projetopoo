
package br.ufg.inf.projetopoo.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ufg.inf.projetopoo.exceptions.WrongInputException;
import br.ufg.inf.projetopoo.models.Admin;
import br.ufg.inf.projetopoo.models.Cliente;
import br.ufg.inf.projetopoo.models.Locadora;
import br.ufg.inf.projetopoo.models.LocadoraAdmin;
import br.ufg.inf.projetopoo.models.Localizacao;
import br.ufg.inf.projetopoo.models.Veiculo;
import br.ufg.inf.projetopoo.repositories.AdminRepository;
import br.ufg.inf.projetopoo.repositories.ClienteRepository;
import br.ufg.inf.projetopoo.repositories.LocadoraAdminRepository;
import br.ufg.inf.projetopoo.repositories.LocadoraRepository;
import br.ufg.inf.projetopoo.repositories.LocalizacaoRepository;
import br.ufg.inf.projetopoo.repositories.VeiculoRepository;

@Component
public class Principal extends JFrame implements CommandLineRunner {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LocalizacaoRepository localizacaoRepository;

	@Autowired
	private LocadoraRepository locadoraRepository;

	@Autowired
	private LocadoraAdminRepository locadoraAdminRepository;

	@Override
	public void run(String... args) throws Exception {
		this.menu();
	}

	public void menu() {
		while (true) {
			List<Admin> admins = adminRepository.findAll();
			if (admins.size() > 0) {
				Integer opcao = -1;

				try {
					opcao = Integer.parseInt(JOptionPane.showInputDialog("1- Entrar como Administrador Geral\n"
							+ "2- Entrar como Administrador de Locadora\n" + "3- Entrar como Cliente\n0- Sair"));

					if (opcao < 0 || opcao > 3) {
						throw new Exception();
					}
				} catch (Exception e) {
					new WrongInputException("Erro! Digite uma op????o V??lida");
				}

				boolean authAdmin = false;
				if (opcao == 1) {
					authAdmin = this.loginAdmin();
					if (authAdmin) {
						while (true) {
							Integer opcaoAdmin = -1;

							try {
								opcaoAdmin = Integer.parseInt(JOptionPane.showInputDialog(
										"1- Adicionar Locadora\n2- Ver Locadoras\n3- Deletar Locadora\n4- Editar Locadora\n5- Adicionar Administrador de Locadora\n6- Ver Administradores de Locadora\n7- Deletar Administrador de Locadora\n8- Editar Administrador de Locadora\n0- Logout"));

								if (opcaoAdmin < 0 || opcaoAdmin > 8) {
									throw new Exception();
								}
							} catch (Exception e) {
								new WrongInputException("Erro! Digite uma op????o V??lida");
							}
							if (opcaoAdmin == 1) {
								this.adicionarLocadora();
							} else if (opcaoAdmin == 2) {
								this.verLocadoras();
							} else if (opcaoAdmin == 3) {
								this.deletarLocadora(this.selecionarLocadora());
							} else if (opcaoAdmin == 4) {
								this.editarLocadora(this.selecionarLocadora());
							} else if (opcaoAdmin == 5) {
								this.cadastraLocadoraAdmin();
							} else if (opcaoAdmin == 6) {
								this.verLocadoraAdmins();
							} else if (opcaoAdmin == 7) {
								this.deletarLocadoraAdmin(this.selecionarLocadoraAdmin());
							} else if (opcaoAdmin == 8) {
								this.editarLocadoraAdmin(this.selecionarLocadoraAdmin());
							} else if (opcaoAdmin == 0) {
								break;
							}
						}
					}
				} else if (opcao == 2) {
					LocadoraAdmin locadoraAdminAutenticado = this.loginLocadoraAdmin();
					if (locadoraAdminAutenticado != null) {
						while (true) {
							Integer opcaoLocadoraAdmin = -1;
							try {
								opcaoLocadoraAdmin = Integer.parseInt(JOptionPane.showInputDialog(
										"1- Adicionar Ve??culo\n2- Ver Ve??culos\n3- Deletar Ve??culo\n4- Editar Ve??culo\n0- Logout"));

								if (opcaoLocadoraAdmin < 0 || opcaoLocadoraAdmin > 4) {
									throw new Exception();
								}
							} catch (Exception e) {
								new WrongInputException("Erro! Digite uma op????o V??lida");
							}

							if (opcaoLocadoraAdmin == 1) {
								this.adicionarVeiculo(locadoraAdminAutenticado.getLocadora());
							} else if (opcaoLocadoraAdmin == 2) {
								this.verVeiculos();
							} else if (opcaoLocadoraAdmin == 3) {
								this.deletarVeiculo(this.selecionarVeiculo());
							} else if (opcaoLocadoraAdmin == 4) {
								this.editarVeiculo(this.selecionarVeiculo());
							} else if (opcaoLocadoraAdmin == 0) {
								break;
							}
						}
					}
				} else if (opcao == 3) {
					Integer opcaoAutenticacaoCliente = -1;
					try {
						opcaoAutenticacaoCliente = Integer.parseInt(JOptionPane.showInputDialog(
								"Como quer entrar?\n1-Fazer Login como Cliente\n2- Cadastrar Cliente"));

						if (opcaoAutenticacaoCliente < 1 || opcaoAutenticacaoCliente > 2) {
							throw new Exception();
						}
					} catch (Exception e) {
						new WrongInputException("Erro! Digite uma op????o V??lida");
					}
					Cliente cliente = null;
					if (opcaoAutenticacaoCliente == 1) {
						cliente = this.loginCliente();
					} else if (opcaoAutenticacaoCliente == 2) {
						this.cadastraCliente();
						cliente = this.loginCliente();
					}
					if (cliente != null) {
						while (true) {
							Integer opcaoCliente = -1;

							try {
								opcaoCliente = Integer.parseInt(JOptionPane.showInputDialog(
										"1- Buscar Ve??culo\n2- Ver Ve??culo Alugado\n3- Desalugar Ve??culo\n0- Logout"));
								if (opcaoCliente < 0 || opcaoCliente > 3) {
									throw new Exception();
								}
							} catch (Exception e) {
								new WrongInputException("Erro! Digite uma op????o V??lida");
							}
							if (opcaoCliente == 1) {
								this.alugarVeiculo(cliente);
							} else if (opcaoCliente == 2) {
								this.verVeiculoAlugado(cliente);
							} else if (opcaoCliente == 3) {
								this.desalugarVeiculo(cliente);
							} else if (opcaoCliente == 0) {
								break;
							}
						}
					}
				} else if (opcao == 0) {
					break;
				}

			} else {
				this.cadastraAdmin();
			}
		}

	}

	public void adicionarLocadora() {
		String endereco = JOptionPane.showInputDialog("Digite o endere??o da Locadora: ");
		String bairro = JOptionPane.showInputDialog("Digite o bairro da Locadora: ");
		String cidade = JOptionPane.showInputDialog("Digite a cidade da Locadora: ");
		String estado = JOptionPane.showInputDialog("Digite o estado da Locadora: ");

		Localizacao localizacao = new Localizacao(endereco, bairro, cidade, estado);

		String nome = JOptionPane.showInputDialog("Digite o nome da Locadora: ");
		String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da Locadora: (Ex: XX. XXX. XXX/0001-XX) ");
		String telefone = JOptionPane.showInputDialog("Digite o telefone da Locadora: (Ex: (XX) XXXXX-XXXX)");

		Locadora locadora = new Locadora(nome, cnpj, telefone, localizacao);
		locadoraRepository.save(locadora);
	}

	public Locadora selecionarLocadora() {
		List<Locadora> locadoras = locadoraRepository.findAll();
		String mensagem = "";

		if (locadoras.size() > 0) {
			mensagem = "Selecione uma Locadora: \n";
			for (Locadora locadora : locadoras) {
				mensagem += "[" + locadora.getId() + "] - " + locadora.getNome() + "\n";
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ainda n??o h?? locadoras cadastradas!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		while (true) {
			try {
				Integer idLocadora = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				Optional<Locadora> locadora = locadoraRepository.findById(idLocadora);

				if (locadora.isPresent()) {
					return locadora.get();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Locadora inv??lida Informada.");
			}
		}
	}

	public void verLocadoras() {
		List<Locadora> locadoras = locadoraRepository.findAll();

		if (locadoras.size() > 0) {
			String mensagem = "Locadoras: \n";
			for (Locadora locadora : locadoras) {
				mensagem += "[" + locadora.getId() + "] - " + locadora.getNome() + "\n";
			}
			JOptionPane.showMessageDialog(null, mensagem);
		} else {
			JOptionPane.showMessageDialog(null, "Ainda n??o h?? locadoras cadastradas!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean deletarLocadora(Locadora locadora) {
		if (locadora != null) {
			locadoraRepository.delete(locadora);
			JOptionPane.showMessageDialog(null, "Locadora Deletada com Sucesso!");
			return true;
		} else {
			return false;
		}
	}

	public Locadora editarLocadora(Locadora locadora) {
		if (locadora != null) {
			Localizacao localizacao = locadora.getLocalizacao();
			Integer opcao = -1;
			try {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja editar:\n\n" + "1- Nome: "
						+ locadora.getNome() + "\n" + "2- CNPJ: " + locadora.getCnpj() + "\n" + "3- Telefone: "
						+ locadora.getTelefone() + "\n" + "4- Endere??o: " + locadora.getLocalizacao().getEndereco()
						+ "\n" + "5- Bairro: " + locadora.getLocalizacao().getBairro() + "\n" + "6- Cidade: "
						+ locadora.getLocalizacao().getCidade() + "\n" + "7- Estado: "
						+ locadora.getLocalizacao().getEstado() + "\n"));
				if (opcao < 1 || opcao > 7) {
					throw new Exception();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Digite uma op????o V??lida");
			}

			if (opcao == 1) {
				String nome = JOptionPane.showInputDialog("Digite o novo nome: ");
				locadora.setNome(nome);
			} else if (opcao == 2) {
				String cnpj = JOptionPane.showInputDialog("Digite o novo CNPJ: ");
				locadora.setCnpj(cnpj);
			} else if (opcao == 3) {
				String telefone = JOptionPane.showInputDialog("Digite o novo Telefone: ");
				locadora.setTelefone(telefone);
			} else if (opcao == 4) {
				String endereco = JOptionPane.showInputDialog("Digite o novo Endere??o: ");
				localizacao.setEndereco(endereco);
				locadora.setLocalizacao(localizacao);
			} else if (opcao == 5) {
				String bairro = JOptionPane.showInputDialog("Digite o novo Bairro: ");
				localizacao.setBairro(bairro);
				locadora.setLocalizacao(localizacao);
			} else if (opcao == 6) {
				String cidade = JOptionPane.showInputDialog("Digite o novo Cidade: ");
				localizacao.setCidade(cidade);
				locadora.setLocalizacao(localizacao);
			} else if (opcao == 7) {
				String estado = JOptionPane.showInputDialog("Digite o novo Estado: ");
				localizacao.setEstado(estado);
				locadora.setLocalizacao(localizacao);
			}

			locadoraRepository.save(locadora);
			localizacaoRepository.save(localizacao);
			return locadora;
		} else {
			return null;
		}
	}

	public Admin cadastraAdmin() {
		String nome = JOptionPane.showInputDialog("Cadastro de Administrador Geral\nDigite o nome do Admin:");
		String sobrenome = JOptionPane.showInputDialog("Cadastro de Administrador Geral\nDigite o sobrenome do Admin:");
		String username = JOptionPane.showInputDialog("Cadastro de Administrador Geral\nDigite o username do Admin:");
		String senha = JOptionPane.showInputDialog("Cadastro de Administrador Geral\nDigite a senha do Admin:");
		String email = JOptionPane.showInputDialog("Cadastro de Administrador Geral\nDigite o email do Admin:");

		Admin admin = new Admin(username, senha, email, nome, sobrenome);
		adminRepository.save(admin);

		return admin;
	}

	public boolean loginAdmin() {
		List<Admin> admins = adminRepository.findAll();
		Admin administrador = null;

		String username = JOptionPane.showInputDialog("Informe seu Usu??rio:");
		boolean validUsername = false;

		for (Admin admin : admins) {
			if (admin.getUsername().equals(username)) {
				validUsername = true;
				administrador = admin;
				break;
			}
		}

		boolean validPassword = false;
		if (validUsername) {
			String senha = JOptionPane.showInputDialog("Informe sua Senha:");
			if (administrador.getSenha().equals(senha)) {
				validPassword = true;
			} else {
				JOptionPane.showMessageDialog(null, "Senha errada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nome de usu??rio inv??lido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return validUsername && validPassword;
	}

	public Cliente cadastraCliente() {
		String nome = JOptionPane.showInputDialog("Cadastro de Cliente\nDigite o nome do Cliente:");
		String sobrenome = JOptionPane.showInputDialog("Cadastro de Cliente\nDigite o sobrenome do Cliente:");
		String username = JOptionPane.showInputDialog("Cadastro de Cliente\nDigite o username do Cliente:");
		String senha = JOptionPane.showInputDialog("Cadastro de Cliente\nDigite a senha do Cliente:");
		String email = JOptionPane.showInputDialog("Cadastro de Cliente\nDigite o email do Cliente:");

		Cliente cliente = new Cliente(username, senha, email, nome, sobrenome);
		clienteRepository.save(cliente);

		return cliente;
	}

	public Cliente loginCliente() {
		List<Cliente> clientes = clienteRepository.findAll();
		Cliente cliente = null;

		String username = JOptionPane.showInputDialog("Informe seu Usu??rio:");
		boolean validUsername = false;

		for (Cliente c : clientes) {
			if (c.getUsername().equals(username)) {
				validUsername = true;
				cliente = c;
				break;
			}
		}

		boolean validPassword = false;
		if (validUsername) {
			String senha = JOptionPane.showInputDialog("Informe sua Senha:");
			if (cliente.getSenha().equals(senha)) {
				validPassword = true;
			} else {
				JOptionPane.showMessageDialog(null, "Senha errada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nome de usu??rio inv??lido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		if (validUsername && validPassword) {
			return cliente;
		} else {
			return null;
		}
	}

	public void cadastraLocadoraAdmin() {
		Locadora locadora = this.selecionarLocadora();

		if (locadora != null) {
			String nome = JOptionPane
					.showInputDialog("Cadastro de LocadoraAdmin\nDigite o nome do Administrador de Locadora:");
			String sobrenome = JOptionPane
					.showInputDialog("Cadastro de LocadoraAdmin\nDigite o sobrenome do Administrador de Locadora:");
			String username = JOptionPane
					.showInputDialog("Cadastro de LocadoraAdmin\nDigite o username do Administrador de Locadora:");
			String senha = JOptionPane
					.showInputDialog("Cadastro de LocadoraAdmin\nDigite a senha do Administrador de Locadora:");
			String email = JOptionPane
					.showInputDialog("Cadastro de LocadoraAdmin\nDigite o email do Administrador de Locadora:");

			LocadoraAdmin locadoraAdmin = new LocadoraAdmin(locadora, username, senha, email, nome, sobrenome);
			locadoraAdminRepository.save(locadoraAdmin);
		}
	}

	public LocadoraAdmin selecionarLocadoraAdmin() {
		List<LocadoraAdmin> locadoraAdmins = locadoraAdminRepository.findAll();
		String mensagem = "";

		if (locadoraAdmins.size() > 0) {
			mensagem = "Selecione um Administrador de Locadora: \n";
			for (LocadoraAdmin locadoraAdmin : locadoraAdmins) {
				mensagem += "[" + locadoraAdmin.getId() + "] - " + locadoraAdmin.getNome() + "\n";
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ainda n??o h?? administradores de locadora cadastrados!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		while (true) {
			try {
				Integer idLocadoraAdmin = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				Optional<LocadoraAdmin> locadoraAdmin = locadoraAdminRepository.findById(idLocadoraAdmin);

				if (locadoraAdmin.isPresent()) {
					return locadoraAdmin.get();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Administrador de Locadora inv??lido Informado.");
			}
		}
	}

	public void verLocadoraAdmins() {
		List<LocadoraAdmin> locadoraAdmins = locadoraAdminRepository.findAll();

		if (locadoraAdmins.size() > 0) {
			String mensagem = "Administradores de Locadora: \n";
			for (LocadoraAdmin locadoraAdmin : locadoraAdmins) {
				mensagem += "[" + locadoraAdmin.getId() + "] - " + locadoraAdmin.getNome() + " "
						+ locadoraAdmin.getSobrenome() + "\n";
			}
			JOptionPane.showMessageDialog(null, mensagem);
		} else {
			JOptionPane.showMessageDialog(null, "Ainda n??o h?? administradores de locadora cadastrados!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean deletarLocadoraAdmin(LocadoraAdmin locadoraAdmin) {
		if (locadoraAdmin != null) {
			locadoraAdminRepository.delete(locadoraAdmin);
			JOptionPane.showMessageDialog(null, "Administrador de Locadora removido com Sucesso!");
			return true;
		} else {
			return false;
		}
	}

	public LocadoraAdmin editarLocadoraAdmin(LocadoraAdmin locadoraAdmin) {
		if (locadoraAdmin != null) {
			Integer opcao = -1;
			try {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja editar:\n\n" + "1- Usu??rio: "
						+ locadoraAdmin.getUsername() + "\n" + "2- Senha: " + locadoraAdmin.getSenha() + "\n"
						+ "3- Email: " + locadoraAdmin.getEmail() + "\n" + "4- Nome: " + locadoraAdmin.getNome() + "\n"
						+ "5- Sobrenome: " + locadoraAdmin.getSobrenome()));
				if (opcao < 1 || opcao > 5) {
					throw new Exception();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Digite uma op????o V??lida");
			}

			if (opcao == 1) {
				String username = JOptionPane.showInputDialog("Digite o novo Usu??rio: ");
				locadoraAdmin.setNome(username);
			} else if (opcao == 2) {
				String senha = JOptionPane.showInputDialog("Digite a nova Senha: ");
				locadoraAdmin.setSenha(senha);
			} else if (opcao == 3) {
				String email = JOptionPane.showInputDialog("Digite o novo email: ");
				locadoraAdmin.setEmail(email);
			} else if (opcao == 4) {
				String nome = JOptionPane.showInputDialog("Digite o novo Nome: ");
				locadoraAdmin.setNome(nome);
			} else if (opcao == 5) {
				String sobrenome = JOptionPane.showInputDialog("Digite o novo Sobrenome: ");
				locadoraAdmin.setSobrenome(sobrenome);
			}

			locadoraAdminRepository.save(locadoraAdmin);
			return locadoraAdmin;
		} else {
			return null;
		}
	}

	public LocadoraAdmin loginLocadoraAdmin() {
		List<LocadoraAdmin> locadoraAdmins = locadoraAdminRepository.findAll();
		LocadoraAdmin locadoraAdmin = null;

		String username = JOptionPane.showInputDialog("Informe seu Usu??rio:");
		boolean validUsername = false;

		for (LocadoraAdmin la : locadoraAdmins) {
			if (la.getUsername().equals(username)) {
				validUsername = true;
				locadoraAdmin = la;
				break;
			}
		}

		boolean validPassword = false;
		if (validUsername) {
			String senha = JOptionPane.showInputDialog("Informe sua Senha:");
			if (locadoraAdmin.getSenha().equals(senha)) {
				validPassword = true;
			} else {
				JOptionPane.showMessageDialog(null, "Senha errada!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nome de usu??rio inv??lido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		if (validUsername && validPassword) {
			return locadoraAdmin;
		} else {
			return null;
		}
	}

	public void adicionarVeiculo(Locadora locadora) {
		String codigo = JOptionPane.showInputDialog("Digite o c??digo do Ve??culo: ");
		String marca = JOptionPane.showInputDialog("Digite a marca do Ve??culo: ");
		String modelo = JOptionPane.showInputDialog("Digite o modelo do Ve??culo: ");
		String ano = JOptionPane.showInputDialog("Digite o ano do Ve??culo: ");

		Double preco = null;
		while (true) {
			try {
				preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o pre??o do Ve??culo: (Ex: 39990.99"));
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Digite um pre??o v??lido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		}
		String categoria = JOptionPane.showInputDialog("Digite a categoria do Ve??culo: ");
		String acessorios = JOptionPane.showInputDialog("Digite os acess??rios do Ve??culo: ");

		Veiculo veiculo = new Veiculo(locadora, codigo, marca, modelo, ano, acessorios, preco, categoria);
		veiculoRepository.save(veiculo);
	}

	public Veiculo selecionarVeiculo() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		String mensagem = "";

		if (veiculos.size() > 0) {
			mensagem = "Selecione um Ve??culo: \n";
			for (Veiculo veiculo : veiculos) {
				mensagem += "[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo() + "\n";
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ainda n??o h?? ve??culos cadastrados!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		while (true) {
			try {
				Integer idVeiculo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
				Optional<Veiculo> veiculo = veiculoRepository.findById(idVeiculo);

				if (veiculo.isPresent()) {
					return veiculo.get();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Ve??culo inv??lido Informado.");
			}
		}
	}

	public void verVeiculos() {
		List<Veiculo> veiculos = veiculoRepository.findAll();

		if (veiculos.size() > 0) {
			String mensagem = "Ve??culos: \n";
			for (Veiculo veiculo : veiculos) {
				mensagem += "[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo() + "\n";
			}
			JOptionPane.showMessageDialog(null, mensagem);
		} else {
			JOptionPane.showMessageDialog(null, "Ainda n??o h?? ve??culos cadastrados!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean deletarVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			veiculoRepository.delete(veiculo);
			JOptionPane.showMessageDialog(null, "Ve??culo Deletado com Sucesso!");
			return true;
		} else {
			return false;
		}
	}

	public Veiculo editarVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			Integer opcao = -1;
			try {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja editar:\n\n" + "1- C??digo: "
						+ veiculo.getCodigo() + "\n" + "2- Marca: " + veiculo.getMarca() + "\n" + "3- Modelo: "
						+ veiculo.getModelo() + "\n" + "4- Ano: " + veiculo.getAno() + "\n" + "5- Acess??rios: "
						+ veiculo.getAcessorios() + "\n" + "6- Pre??o: " + veiculo.getPreco() + "\n" + "7- Categoria: "
						+ veiculo.getCategoria() + "\n"));
				if (opcao < 1 || opcao > 7) {
					throw new Exception();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Digite uma op????o V??lida");
			}

			if (opcao == 1) {
				String codigo = JOptionPane.showInputDialog("Digite o novo C??digo: ");
				veiculo.setCodigo(codigo);
			} else if (opcao == 2) {
				String marca = JOptionPane.showInputDialog("Digite a nova Marca: ");
				veiculo.setMarca(marca);
			} else if (opcao == 3) {
				String modelo = JOptionPane.showInputDialog("Digite o novo Modelo: ");
				veiculo.setModelo(modelo);
			} else if (opcao == 4) {
				String ano = JOptionPane.showInputDialog("Digite o novo Ano: ");
				veiculo.setAno(ano);
			} else if (opcao == 5) {
				String acessorios = JOptionPane.showInputDialog("Digite os novos Acess??rios: ");
				veiculo.setAcessorios(acessorios);
			} else if (opcao == 6) {
				Double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo Pre??o: "));
				veiculo.setPreco(preco);
			} else if (opcao == 7) {
				String categoria = JOptionPane.showInputDialog("Digite a nova Categoria: ");
				veiculo.setCategoria(categoria);
			}

			veiculoRepository.save(veiculo);
			return veiculo;
		} else {
			return null;
		}
	}

	public List<Veiculo> buscarVeiculo() {
		Integer opcaoBusca = -1;
		try {
			opcaoBusca = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite o par??metro de busca:\n1- Ve??culo por Marca\n2- Ve??culo por Modelo\n3- Ve??culo por ano\n4- Ve??culo por Localiza????o"));
			if (opcaoBusca < 1 || opcaoBusca > 4) {
				throw new Exception();
			}
		} catch (Exception e) {
			new WrongInputException("Erro! Digite uma op????o V??lida");
		}

		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		if (opcaoBusca == 1) {
			String marca = JOptionPane.showInputDialog("Digite a marca do Ve??culo");
			veiculos = veiculoRepository.findByMarca(marca);
		} else if (opcaoBusca == 2) {
			String modelo = JOptionPane.showInputDialog("Digite o modelo do Ve??culo");
			veiculos = veiculoRepository.findByModelo(modelo);
		} else if (opcaoBusca == 3) {
			String ano = JOptionPane.showInputDialog("Digite o ano do Ve??culo");
			veiculos = veiculoRepository.findByAno(ano);
		} else if (opcaoBusca == 4) {
			List<Localizacao> localizacoes = this.buscarLocalizacao();
			for (Veiculo v : veiculoRepository.findAll()) {
				for (Localizacao l : localizacoes) {
					if (v.getLocadora().getLocalizacao().toString() == l.toString()) {
						veiculos.add(v);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Op????o Inv??lida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return veiculos;
	}

	public Veiculo alugarVeiculo(Cliente cliente) {
		List<Veiculo> veiculos = this.buscarVeiculo();

		if (veiculos.size() > 0) {
			String mensagem = "Selecione o Ve??culo para alugar:\n";
			for (Veiculo veiculo : veiculos) {
				mensagem += "[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo() + "\n";
			}

			Veiculo veiculo = null;
			while (true) {
				try {
					Integer idVeiculo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
					Optional<Veiculo> veiculoOptional = veiculoRepository.findById(idVeiculo);

					if (veiculoOptional.isPresent()) {
						veiculo = veiculoOptional.get();
						break;
					}
				} catch (Exception e) {
					new WrongInputException("Erro! Ve??culo inv??lido Informado.");
				}
			}

			cliente.setVeiculo(veiculo);
			clienteRepository.save(cliente);
			for (Veiculo v : veiculos) {
				v.setCliente(cliente);
				veiculoRepository.save(v);
			}

			JOptionPane.showMessageDialog(null, "Ve??culo alugado com sucesso!");
			return veiculo;
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum ve??culo corresponde ?? consulta!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public Veiculo verVeiculoAlugado(Cliente cliente) {
		Veiculo veiculo = cliente.getVeiculo();

		if (veiculo != null) {
			JOptionPane.showMessageDialog(null,
					"Ve??culo:\n[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo());
		} else {
			JOptionPane.showMessageDialog(null, "Cliente sem ve??culo Alugado!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return veiculo;
	}

	public void desalugarVeiculo(Cliente cliente) {
		if (cliente.getVeiculo() != null) {
			cliente.setVeiculo(null);
			clienteRepository.save(cliente);
			JOptionPane.showMessageDialog(null, "Ve??culo Desalugado com Sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Cliente sem ve??culo Alugado!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}
	}

	public List<Localizacao> buscarLocalizacao() {

		Integer opcaoLocalizacao = -1;
		try {
			opcaoLocalizacao = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite o par??metro de busca da localiza????o:\n1- Endere??o\n2- Bairro\n3- Cidade\n4- Estado"));
			if (opcaoLocalizacao < 1 || opcaoLocalizacao > 4) {
				throw new Exception();
			}
		} catch (Exception e) {
			new WrongInputException("Erro! Digite uma op????o V??lida");
		}

		List<Localizacao> localizacoes = null;
		if (opcaoLocalizacao == 1) {
			String endereco = JOptionPane.showInputDialog("Digite o endere??o da localiza????o:");
			localizacoes = localizacaoRepository.findByEndereco(endereco);
		} else if (opcaoLocalizacao == 2) {
			String bairro = JOptionPane.showInputDialog("Digite o bairro da localiza????o:");
			localizacoes = localizacaoRepository.findByBairro(bairro);
		} else if (opcaoLocalizacao == 3) {
			String cidade = JOptionPane.showInputDialog("Digite a cidade da localiza????o:");
			localizacoes = localizacaoRepository.findByCidade(cidade);
		} else if (opcaoLocalizacao == 4) {
			String estado = JOptionPane.showInputDialog("Digite o estado da localiza????o:");
			localizacoes = localizacaoRepository.findByEstado(estado);
		} else {
			JOptionPane.showMessageDialog(null, "Op????o Inv??lida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return localizacoes;
	}
}
