package ma.emsi.db_livre;

import ma.emsi.db_livre.entities.Exposant;
import ma.emsi.db_livre.entities.Livre;
import ma.emsi.db_livre.repositories.ExposantRepository;
import ma.emsi.db_livre.repositories.LivreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class DbLivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbLivreApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!jdbcUserDetailsManager.userExists("user")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }
            if (!jdbcUserDetailsManager.userExists("exposant")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("exposant").password(passwordEncoder.encode("1234")).roles("USER", "EXPOSANT").build()
                );
            }
            if (!jdbcUserDetailsManager.userExists("admin")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "EXPOSANT", "ADMIN").build()
                );
            }
            System.out.println("CommandLineRunner commandLineRunner");
        };
    }




    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
