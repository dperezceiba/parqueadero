package com.co.ceiba.establecimiento.integracion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CeibaEstablecimientoApplication.class)
//@WebAppConfiguration
public class EntradaIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntradaIntegrationTest.class);

//	@Autowired
//	private WebApplicationContext webServerAppContext;
//
//	private MockMvc webServerMockMvc;
//
//	@Before
//	public void setUp() {
//		LOGGER.info("The test is set up!");
//		webServerMockMvc = MockMvcBuilders.webAppContextSetup(webServerAppContext).build();
//	}
//
//	@Test
//	public void registrarIngresoCarroTest() throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		Carro carro = new CarroTestDataBuilder().build();
//		
//		this.webServerMockMvc
//				.perform(MockMvcRequestBuilders.post("/entrada/carro").contentType(MediaType.APPLICATION_JSON)
//						.content(mapper.writeValueAsString(carro)))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

}
