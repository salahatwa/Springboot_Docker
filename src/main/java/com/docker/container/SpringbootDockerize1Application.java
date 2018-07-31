package com.docker.container;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.docker.container.entities.Product;
import com.docker.container.entities.Role;
import com.docker.container.entities.User;
import com.docker.container.repo.ProductRepo;
import com.docker.container.service.UserService;

//cross site scripting
/**
 * http://localhost:8085/oauth/token?grant_type=password&username=atwa&password=salah
 * http://localhost:8085/api/home
 * http://localhost:8085/api/user/me?access_token=dd9f8111-e8d3-4243-9d6f-13b62511ba37
 * 
 * @author atwa Jun 23, 2018
 */
@SpringBootApplication
public class SpringbootDockerize1Application {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductRepo productRepo;

	/**
	 * @return the productRepo
	 */
	public ProductRepo getProductRepo() {
		return productRepo;
	}

	/**
	 * @param productRepo
	 *            the productRepo to set
	 */
	public void setProductRepo(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	@Autowired
	private EntityManager entityManager;

	static String x;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDockerize1Application.class, args);

		// System.err.println(x.trim());

	}

	// @Autowired
	public void getUser() {
		Query query = entityManager.createNativeQuery("select max(id) from User");
		query.setMaxResults(1);

		Object object = query.getSingleResult();

		BigInteger ex = (BigInteger) object;
		// System.out.println(">>>>>>>>>>>>>" + ex.longValue());
	}

//	 @Autowired
	/**
	 * http://localhost:8085/oauth/token?grant_type=password&username=atwa&password=salah
	 */
	public void insertUser() {
		User user = new User();
		user.setUsername("wael2");
		user.setPassword(encoder.encode("salah123"));
		user.setRoles(Arrays.asList(new Role("USER"), new Role("ACTUATOR")));
		user.setAge(50);

		user = userService.save(user);

		Product p = new Product();
		p.setProductName("Book");
		p.setUser_id(user.getId());

		productRepo.save(p);
	}

//	@Autowired
	public void getUserRoles() {
		Query query = entityManager
				.createNativeQuery("select {u.*}  , {p.*} from User u inner join Products p on u.id=p.user_id");

		List list = query.getResultList();

		for (int i = 0; i < list.size(); i++) {
			 System.out.println(((User[]) list.get(i))[0]); // account bean, actually this
			 System.out.println(((Product[]) list.get(i))[1]); // user bean & this account

//			System.out.print(list.get(i) + " , ");
		}

		System.out.println(">>>>>" + list.size());
		// System.out.println(">>>>>>>>>>>>>" + ex.longValue());
	}

	// 7cd4dc6c-d173-4490-a221-cd3d607ac12b atwa
	// dbe93227-617b-467e-b096-bde39acfd296 russia

	/*
	 * @Autowired public void authenticationManager(AuthenticationManagerBuilder
	 * builder, UserRepo repo) throws Exception { builder.userDetailsService(new
	 * UserDetailsService() { [
	 * 
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { return new
	 * C.ustomUserDetails(repo.findByUsername(username)); } }); }
	 */

}
