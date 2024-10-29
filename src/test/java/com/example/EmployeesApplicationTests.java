package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yaml")
class EmployeesApplicationTests {

   private static Logger logger = LoggerFactory.getLogger(EmployeesApplicationTests.class);

   @Autowired
   private MockMvc mvc;

   @Test
   void ChausetteTest() throws Exception {
      logger.info("___________  Test chausette  ___________");

      enum Clothes {
         SOCKS("Chausettes"), DIRTY("Sales"), SOCKSDIRTY("ChausettesSales");

         private String clothe;

         private Clothes(String pathology) {
            this.clothe = pathology;
         }

         public String getValue() {
            return this.clothe;
         }
      }

      for (int loop = 1; loop < 101; ++loop) {
         if (loop % 3 == 0 && loop % 5 == 0) {
            // multiple de 3 et de 5 imprimer "ChausettesSales"
            logger.info(Clothes.SOCKSDIRTY.getValue());
         } else if (loop % 3 == 0) {
            // multiple de 3 imprimer "Chausettes"
            logger.info(Clothes.SOCKS.getValue());
         } else if (loop % 5 == 0) {
            // multiple de 5 imprimer "Sales"
            logger.info(Clothes.DIRTY.getValue());
         } else {
            // sinon imprimer le nombre
            logger.info("" + loop);
         }
      }
   }

   @Test
   void contextLoads() throws Exception {

      String responseBody = null;
      MvcResult result = null;
      MockHttpServletRequestBuilder build = null;

      logger.info("___________  Create services  ___________");
      build = MockMvcRequestBuilders.post("/itsf/service");
      build.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

      // @formatter:off
      build.content(
            "[{\"name\": \"service1\"}, " + 
            " {\"name\": \"service2\", \"parent\": \"service1\"}, " +
            " {\"name\": \"service3\"}]");
      // @formatter:on

      result = mvc.perform(build.accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            /* .andDo(MockMvcResultHandlers.print()) */.andReturn();
      responseBody = result.getResponse().getContentAsString();
      logger.info(responseBody);

      logger.info("___________  Create employee  ___________");
      build = MockMvcRequestBuilders.post("/itsf/employee");
      build.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

      // @formatter:off
      build.content(
            "[{\"firstname\": \"John\", \"lastname\": \"Daniel\", \"birthdate\": \"2000-10-27T00:00:00Z\", \"manager\": true," + 
            "  \"contract\": [{ \"signature\": \"2024-10-27T00:00:00Z\", \"duration\": 365, \"salary\": 5000.99 }]," +
            "  \"service\": [{ \"name\": \"service1\"}]}," +
            "{\"firstname\": \"Jack\", \"lastname\": \"Doom\", \"birthdate\": \"1999-02-05T00:00:00Z\", \"manager\": false," + 
            " \"parent\": [{ \"firstname\": \"John\", \"lastname\": \"Daniel\", \"family_ties\": \"brother\" }]," + 
            " \"contract\": [{ \"signature\": \"2010-05-01T00:00:00Z\", \"duration\": 900, \"salary\": 6950.12 }]," +
            " \"service\": [{ \"name\": \"service2\"}]}]");
      // @formatter:on

      result = mvc.perform(build.accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            /* .andDo(MockMvcResultHandlers.print()) */.andReturn();
      responseBody = result.getResponse().getContentAsString();
      logger.info(responseBody);

   }

}
