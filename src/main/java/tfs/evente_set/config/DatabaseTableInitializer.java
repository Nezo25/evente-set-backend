package tfs.evente_set.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTableInitializer implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        try {
            jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS cardapio_itens (" +
                "  cardapio_id BIGINT NOT NULL," +
                "  item_cardapio_id BIGINT NOT NULL," +
                "  PRIMARY KEY (cardapio_id, item_cardapio_id)," +
                "  CONSTRAINT fk_ci_cardapio FOREIGN KEY (cardapio_id) REFERENCES cardapios (id) ON DELETE CASCADE," +
                "  CONSTRAINT fk_ci_item FOREIGN KEY (item_cardapio_id) REFERENCES itens_cardapio (id) ON DELETE CASCADE" +
                ")"
            );
            System.out.println("Tabela cardapio_itens verificada/criada com sucesso!");
        } catch (Exception e) {
            System.err.println("Aviso ao inicializar cardapio_itens: " + e.getMessage());
        }

        try {
            jdbcTemplate.execute("ALTER TABLE itens_cardapio MODIFY COLUMN categoria VARCHAR(50) NOT NULL");
            System.out.println("Coluna categoria de itens_cardapio atualizada para VARCHAR(50)!");
        } catch (Exception e) {
            System.err.println("Aviso ao atualizar coluna categoria: " + e.getMessage());
        }
    }
}
