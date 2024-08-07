package ru.rosatom.edu.dariayo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class StorageControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testCreateFile() throws Exception {
                String fileMetadataJson = "{ \"title\": \"Test File\", \"creationDate\": \"2024-08-06T12:12:12\", \"description\": \"Test Description\", \"file\": \"SGVsbG8gV29ybGQ=\" }";

                mockMvc.perform(post("/api/files")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(fileMetadataJson))
                                .andExpect(status().isOk());
        }

        @Test
        public void testGetFile() throws Exception {
                String fileMetadataJson = "{ \"title\": \"Test File\", \"creationDate\": \"2024-08-06T12:12:13\", \"description\": \"Test Description\", \"file\": \"SGVsbG8gV29ybGQ=\" }";

                mockMvc.perform(post("/api/files")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(fileMetadataJson))
                                .andExpect(status().isOk());

                mockMvc.perform(get("/api/files/{id}", 2))
                                .andExpect(status().isOk());
        }

        @Test
        public void testGetAllFiles() throws Exception {
                String fileMetadataJson1 = "{ \"title\": \"Test File 1\", \"creationDate\": \"2024-08-06T12:12:12\", \"description\": \"Test Description 1\", \"file\": \"SGVsbG8gV29ybGQx\" }";
                String fileMetadataJson2 = "{ \"title\": \"Test File 2\", \"creationDate\": \"2024-08-06T12:12:12\", \"description\": \"Test Description 2\", \"file\": \"SGVsbG8gV29ybGQy\" }";

                mockMvc.perform(MockMvcRequestBuilders.post("/api/files")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(fileMetadataJson1))
                                .andExpect(status().isOk());

                mockMvc.perform(MockMvcRequestBuilders.post("/api/files")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(fileMetadataJson2))
                                .andExpect(status().isOk());

                mockMvc.perform(get("/api/files")
                                .param("page", "0")
                                .param("size", "10"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[0].title").value("Test File 1"))
                                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                                .andExpect(jsonPath("$[1].title").value("Test File 2"))
                                .andExpect(jsonPath("$[1].description").value("Test Description 2"));
        }

}
