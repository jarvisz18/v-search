package cn.ixan.search;

import cn.ixan.search.domain.Student;
import cn.ixan.search.repository.StudentRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.System.exit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/6 下午9:35
 * @description hikari application
 */
@SpringBootApplication
public class HikariApplication implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private StudentRepository studentRepository;


	public static void main(String[] args) {
		SpringApplication.run(HikariApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(dataSource);
		//打印dataSource的核心线程数
		HikariDataSource dataSource = (HikariDataSource) this.dataSource;
		System.out.println("getMaximumPoolSize:" + dataSource.getMaximumPoolSize());

		if (args.length <= 0) {
			System.out.println("[Usage] java xxx.jar {insert name email | display}");
		} else {
			if ("insert".equalsIgnoreCase(args[0])) {
				System.out.println("Add Student");
				String name = args[1];
				String email = args[2];
				studentRepository.addCustomer(name, email);
			}

			if ("display".equalsIgnoreCase(args[0])) {
				System.out.println("Display all student");
				List<Student> all = studentRepository.findALl();
				all.forEach(System.out::println);
			}
			System.out.println("Done!");
		}
		exit(0);
	}
}
