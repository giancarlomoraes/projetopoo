
package br.ufg.inf.projetopoo.test;

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
public class Test extends JFrame implements CommandLineRunner {

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
					new WrongInputException("Erro! Digite uma opção Válida");
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
								new WrongInputException("Erro! Digite uma opção Válida");
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
										"1- Adicionar Veículo\n2- Ver Veículos\n3- Deletar Veículo\n4- Editar Veículo\n0- Logout"));

								if (opcaoLocadoraAdmin < 1 || opcaoLocadoraAdmin > 4) {
									throw new Exception();
								}
							} catch (Exception e) {
								new WrongInputException("Erro! Digite uma opção Válida");
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
						new WrongInputException("Erro! Digite uma opção Válida");
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
										"1- Buscar Veículo\n2- Ver Veículo Alugado\n3- Desalugar Veículo\n0- Logout"));
								if (opcaoCliente < 0 || opcaoCliente > 3) {
									throw new Exception();
								}
							} catch (Exception e) {
								new WrongInputException("Erro! Digite uma opção Válida");
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
		String endereco = JOptionPane.showInputDialog("Digite o endereço da Locadora: ");
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
			JOptionPane.showMessageDialog(null, "Ainda não há locadoras cadastradas!", "Aviso!",
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
				new WrongInputException("Erro! Locadora inválida Informada.");
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
			JOptionPane.showMessageDialog(null, "Ainda não há locadoras cadastradas!", "Aviso!",
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
						+ locadora.getTelefone() + "\n" + "4- Endereço: " + locadora.getLocalizacao().getEndereco()
						+ "\n" + "5- Bairro: " + locadora.getLocalizacao().getBairro() + "\n" + "6- Cidade: "
						+ locadora.getLocalizacao().getCidade() + "\n" + "7- Estado: "
						+ locadora.getLocalizacao().getEstado() + "\n"));
				if (opcao < 1 || opcao > 7) {
					throw new Exception();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Digite uma opção Válida");
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
				String endereco = JOptionPane.showInputDialog("Digite o novo Endereço: ");
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

		String username = JOptionPane.showInputDialog("Informe seu Usuário:");
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
			JOptionPane.showMessageDialog(null, "Nome de usuário inválido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
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

		String username = JOptionPane.showInputDialog("Informe seu Usuário:");
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
			JOptionPane.showMessageDialog(null, "Nome de usuário inválido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Ainda não há administradores de locadora cadastrados!", "Aviso!",
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
				new WrongInputException("Erro! Administrador de Locadora inválido Informado.");
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
			JOptionPane.showMessageDialog(null, "Ainda não há administradores de locadora cadastrados!", "Aviso!",
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
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja editar:\n\n" + "1- Usuário: "
						+ locadoraAdmin.getUsername() + "\n" + "2- Senha: " + locadoraAdmin.getSenha() + "\n"
						+ "3- Email: " + locadoraAdmin.getEmail() + "\n" + "4- Nome: " + locadoraAdmin.getNome() + "\n"
						+ "5- Sobrenome: " + locadoraAdmin.getSobrenome()));
				if (opcao < 1 || opcao > 5) {
					throw new Exception();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Digite uma opção Válida");
			}

			if (opcao == 1) {
				String username = JOptionPane.showInputDialog("Digite o novo Usuário: ");
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

		String username = JOptionPane.showInputDialog("Informe seu Usuário:");
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
			JOptionPane.showMessageDialog(null, "Nome de usuário inválido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		if (validUsername && validPassword) {
			return locadoraAdmin;
		} else {
			return null;
		}
	}

	public void adicionarVeiculo(Locadora locadora) {
		String codigo = JOptionPane.showInputDialog("Digite o código do Veículo: ");
		String marca = JOptionPane.showInputDialog("Digite a marca do Veículo: ");
		String modelo = JOptionPane.showInputDialog("Digite o modelo do Veículo: ");
		String ano = JOptionPane.showInputDialog("Digite o ano do Veículo: ");

		Double preco = null;
		while (true) {
			try {
				preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do Veículo: (Ex: 39990.99"));
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Digite um preço válido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
			}
		}
		String categoria = JOptionPane.showInputDialog("Digite a categoria do Veículo: ");
		String acessorios = JOptionPane.showInputDialog("Digite os acessórios do Veículo: ");

		Veiculo veiculo = new Veiculo(locadora, codigo, marca, modelo, ano, acessorios, preco, categoria);
		veiculoRepository.save(veiculo);
	}

	public Veiculo selecionarVeiculo() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		String mensagem = "";

		if (veiculos.size() > 0) {
			mensagem = "Selecione um Veículo: \n";
			for (Veiculo veiculo : veiculos) {
				mensagem += "[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo() + "\n";
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ainda não há veículos cadastrados!", "Aviso!",
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
				new WrongInputException("Erro! Veículo inválido Informado.");
			}
		}
	}

	public void verVeiculos() {
		List<Veiculo> veiculos = veiculoRepository.findAll();

		if (veiculos.size() > 0) {
			String mensagem = "Veículos: \n";
			for (Veiculo veiculo : veiculos) {
				mensagem += "[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo() + "\n";
			}
			JOptionPane.showMessageDialog(null, mensagem);
		} else {
			JOptionPane.showMessageDialog(null, "Ainda não há veículos cadastrados!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean deletarVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			veiculoRepository.delete(veiculo);
			JOptionPane.showMessageDialog(null, "Veículo Deletado com Sucesso!");
			return true;
		} else {
			return false;
		}
	}

	public Veiculo editarVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			Integer opcao = -1;
			try {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja editar:\n\n" + "1- Código: "
						+ veiculo.getCodigo() + "\n" + "2- Marca: " + veiculo.getMarca() + "\n" + "3- Modelo: "
						+ veiculo.getModelo() + "\n" + "4- Ano: " + veiculo.getAno() + "\n" + "5- Acessórios: "
						+ veiculo.getAcessorios() + "\n" + "6- Preço: " + veiculo.getPreco() + "\n" + "7- Categoria: "
						+ veiculo.getCategoria() + "\n"));
				if (opcao < 1 || opcao > 7) {
					throw new Exception();
				}
			} catch (Exception e) {
				new WrongInputException("Erro! Digite uma opção Válida");
			}

			if (opcao == 1) {
				String codigo = JOptionPane.showInputDialog("Digite o novo Código: ");
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
				String acessorios = JOptionPane.showInputDialog("Digite os novos Acessórios: ");
				veiculo.setAcessorios(acessorios);
			} else if (opcao == 6) {
				Double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo Preço: "));
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
					"Digite o parâmetro de busca:\n1- Veículo por Marca\n2- Veículo por Modelo\n3- Veículo por ano\n4- Veículo por Localização"));
			if (opcaoBusca < 1 || opcaoBusca > 4) {
				throw new Exception();
			}
		} catch (Exception e) {
			new WrongInputException("Erro! Digite uma opção Válida");
		}

		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		if (opcaoBusca == 1) {
			String marca = JOptionPane.showInputDialog("Digite a marca do Veículo");
			veiculos = veiculoRepository.findByMarca(marca);
		} else if (opcaoBusca == 2) {
			String modelo = JOptionPane.showInputDialog("Digite o modelo do Veículo");
			veiculos = veiculoRepository.findByModelo(modelo);
		} else if (opcaoBusca == 3) {
			String ano = JOptionPane.showInputDialog("Digite o ano do Veículo");
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
			JOptionPane.showMessageDialog(null, "Opção Inválida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return veiculos;
	}

	public Veiculo alugarVeiculo(Cliente cliente) {
		List<Veiculo> veiculos = this.buscarVeiculo();

		if (veiculos.size() > 0) {
			String mensagem = "Selecione o Veículo para alugar:\n";
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
					new WrongInputException("Erro! Veículo inválido Informado.");
				}
			}

			cliente.setVeiculo(veiculo);
			clienteRepository.save(cliente);
			for (Veiculo v : veiculos) {
				v.setCliente(cliente);
				veiculoRepository.save(v);
			}

			JOptionPane.showMessageDialog(null, "Veículo alugado com sucesso!");
			return veiculo;
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum veículo corresponde à consulta!", "Aviso!",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public Veiculo verVeiculoAlugado(Cliente cliente) {
		Veiculo veiculo = cliente.getVeiculo();

		if (veiculo != null) {
			JOptionPane.showMessageDialog(null,
					"Veículo:\n[" + veiculo.getId() + "] - " + veiculo.getMarca() + " " + veiculo.getModelo());
		} else {
			JOptionPane.showMessageDialog(null, "Cliente sem veículo Alugado!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return veiculo;
	}

	public void desalugarVeiculo(Cliente cliente) {
		if (cliente.getVeiculo() != null) {
			cliente.setVeiculo(null);
			clienteRepository.save(cliente);
			JOptionPane.showMessageDialog(null, "Veículo Desalugado com Sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Cliente sem veículo Alugado!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}
	}

	public List<Localizacao> buscarLocalizacao() {

		Integer opcaoLocalizacao = -1;
		try {
			opcaoLocalizacao = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite o parâmetro de busca da localização:\n1- Endereço\n2- Bairro\n3- Cidade\n4- Estado"));
			if (opcaoLocalizacao < 1 || opcaoLocalizacao > 4) {
				throw new Exception();
			}
		} catch (Exception e) {
			new WrongInputException("Erro! Digite uma opção Válida");
		}

		List<Localizacao> localizacoes = null;
		if (opcaoLocalizacao == 1) {
			String endereco = JOptionPane.showInputDialog("Digite o endereço da localização:");
			localizacoes = localizacaoRepository.findByEndereco(endereco);
		} else if (opcaoLocalizacao == 2) {
			String bairro = JOptionPane.showInputDialog("Digite o bairro da localização:");
			localizacoes = localizacaoRepository.findByBairro(bairro);
		} else if (opcaoLocalizacao == 3) {
			String cidade = JOptionPane.showInputDialog("Digite a cidade da localização:");
			localizacoes = localizacaoRepository.findByCidade(cidade);
		} else if (opcaoLocalizacao == 4) {
			String estado = JOptionPane.showInputDialog("Digite o estado da localização:");
			localizacoes = localizacaoRepository.findByEstado(estado);
		} else {
			JOptionPane.showMessageDialog(null, "Opção Inválida!", "Aviso!", JOptionPane.WARNING_MESSAGE);
		}

		return localizacoes;
	}
}
