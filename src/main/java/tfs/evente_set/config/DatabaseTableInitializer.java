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
                "  PRIMARY KEY (cardapio_id, item_cardapio_id)" +
                ")"
            );
            
            // Adiciona tenant_id se necessário para compatibilidade com @TenantId do Hibernate
            try {
                jdbcTemplate.execute("ALTER TABLE cardapio_itens ADD COLUMN tenant_id VARCHAR(255) DEFAULT 'default_tenant'");
            } catch (Exception ignored) {}

            System.out.println("Tabela cardapio_itens verificada/criada com sucesso!");
        } catch (Exception e) {
            System.err.println("Aviso ao inicializar cardapio_itens: " + e.getMessage());
        }
    }
}
