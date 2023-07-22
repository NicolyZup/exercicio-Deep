package service;

import connection.Conexao;

import java.sql.*;

public class CarroService {
    static Connection connection = Conexao.getConnection();

    static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void consultarCarros() {
        String sql = "SELECT carros.nome_carro, carros.placa, carros.data_entrada,carros.hora_entrada, clientes.nome " +
                "FROM carros " +
                "INNER JOIN clientes ON carros.id_cliente = clientes.id";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("carro: " + resultSet.getString("nome_carro")
                        + " | placa: " + resultSet.getString("placa")
                        + " | cliente: " + resultSet.getString("nome")
                        + " | data entrada: " + resultSet.getDate("data_entrada").toLocalDate()
                        + " | hora entrada: " + resultSet.getTime("hora_entrada").toLocalTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void entradaCarro(String nomeCarro, String placa, String nomeCliente, String cpfCliente) {
        try {
            int idCliente = buscarClientePorCPF(cpfCliente);

            if (idCliente == -1) {
                // Cliente não encontrado, cadastrar o novo cliente
                idCliente = cadastrarCliente(nomeCliente, cpfCliente);
            }

            // Registrar a entrada do carro
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO carros (nome_carro, id_cliente, placa, data_entrada, hora_entrada) "
                    + "VALUES (?, ?, ?, CURRENT_DATE, CURRENT_TIME)");
            stmt.setString(1, nomeCarro);
            stmt.setInt(2, idCliente);
            stmt.setString(3, placa);
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Entrada registrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao registrar entrada do carro: " + e.getMessage());
        }
    }

    private static int buscarClientePorCPF(String cpf) {
        int idCliente = -1;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM clientes WHERE cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
        }

        return idCliente;
    }

    private static int cadastrarCliente(String nome, String cpf) {
        int idCliente = -1;

        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO clientes (nome, cpf) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idCliente = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }

        return idCliente;
    }

    public double registrarSaidaCarro(String placa) {
        double valorPermanencia = 0.0;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id, nome_carro, id_cliente, data_entrada, hora_entrada FROM carros WHERE placa = ?");
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idCarro = rs.getInt("id");
                Date dataEntrada = rs.getDate("data_entrada");
                Time horaEntrada = rs.getTime("hora_entrada");

                valorPermanencia = calcularValorPermanencia(dataEntrada, horaEntrada);

                // Registrar a saída do carro na tabela permanências
                stmt = connection.prepareStatement("INSERT INTO permanencias (idCarro, data_saida, hora_saida, valor) VALUES (?, CURRENT_DATE, CURRENT_TIME, ?)");
                stmt.setInt(1, idCarro);
                stmt.setDouble(2, valorPermanencia);
                stmt.executeUpdate();

            } else {
                System.out.println("Carro não encontrado no estacionamento.");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(valorPermanencia);
        return valorPermanencia;
    }

    private static double calcularValorPermanencia(Date dataEntrada, Time horaEntrada) {
        double valorPermanencia ;

        // Obter a data e hora atual
        long dataAtualMillis = System.currentTimeMillis();
        Time horaAtual = new Time(dataAtualMillis);

        // Calcular a diferença entre a data e hora de entrada e a data e hora atual
        long diffMillis = dataAtualMillis - dataEntrada.getTime() + horaAtual.getTime() - horaEntrada.getTime();
        long diffHoras = diffMillis / (60 * 60 * 1000); // Converter para horas


        if (diffHoras <= 1) {
            valorPermanencia = 10.0;
        } else if (diffHoras <= 12) {
            valorPermanencia = 10.0 + ((diffHoras - 1) / 0.5) * 2.0;
        } else {
            valorPermanencia = 90.0;
        }

        return valorPermanencia;
    }

}


