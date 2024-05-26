package com.isis.backend;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebAppConfiguration("classpath:META-INF/web-resources")
class BackendApplicationTests {

	@Autowired
	WebApplicationContext wac;

	@Autowired
	private BookRepository bookRepository;


	WebTestClient client;

	@BeforeEach
	void setUp() {
		client = MockMvcWebTestClient.bindToApplicationContext(this.wac).build();
	}

	@Test
	void verifTitle() {
		client.get().uri("/api/title").accept(MediaType.TEXT_HTML).exchange()
			.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Devops forever");

	}

	@Test
	void verifBooks() {
		Book book = new Book("Les misérables", "Victor Hugo", "123ibs12");
		bookRepository.save(book);
		client.get().uri("/api/books").accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk()
				.expectBodyList(Book.class).hasSize(1)
				.consumeWith(result -> {
					assert(result.getResponseBody().get(0).author.equals("Victor Hugo"));
				});
	}

}
