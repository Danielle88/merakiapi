package br.com.fiap.merakiapi.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.merakiapi.model.Diario;
import br.com.fiap.merakiapi.model.Usuario;
import br.com.fiap.merakiapi.repository.DiarioRepository;
import br.com.fiap.merakiapi.repository.UsuarioRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DiarioRepository diarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        usuarioRepository.saveAll(List.of(

            new Usuario()
                .nome("João")
                .email("joao@fiap.com.br")
                .senha(passwordEncoder.encode("123")),
            new Usuario()
                .nome("Pedro")
                .email("pedro@fiap.com.br")
                .senha(passwordEncoder.encode("plm@67")),
            new Usuario()
                .nome("Luiza")
                .email("luizaml@gmail.com")
                .senha(passwordEncoder.encode("08092001@gh")),
            new Usuario()
                .nome("Tereza")
                .email("tgoncalves@fiap.com.br")
                .senha(passwordEncoder.encode("tiq54@")),
            new Usuario()
                .nome("Paula")
                .email("paula@gmail.com")
                .senha(passwordEncoder.encode("mypassword@1234"))
            
            

        ));

        diarioRepository.saveAll(List.of(
            new Diario("Trabalho e familia","Hoje passei o dia pensando em como meu trabalho....", LocalDate.of(2022, Month.APRIL, 15)),
            new Diario("Cansei","Gostaria de fazer uma reflexão sobre....", LocalDate.of(2022, Month.MAY, 02))

        ));
    }

    
    
}
