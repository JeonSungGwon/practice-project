//package com.example.firstproject.objectmapper;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BuggerTest {
//
//    @Test
//    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
//        // 준비
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
//        Bugger bugger = new Bugger("맥도날드 슈비버거", 5000, ingredients);
//        // 수행
//        String json = objectMapper.writeValueAsString(bugger);
//        // 예상
//        String expected = "{\"name\":\"맥도날드 슈비버거\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
//
//        // 검증
//        assertEquals(expected, json);
//        JsonNode jsonNode = objectMapper.readTree(json);
//        System.out.println(jsonNode.toPrettyString());
//    }
//
//    @Test
//    public void JSON을_자바_객체로_변환() throws JsonProcessingException {
//        // 준비
//        ObjectMapper objectMapper = new ObjectMapper();
//        // 예상
//        /*
//        {
//        "name" : "맥도날드 슈비버거"
//        "price" : 5000
//        "ingredients : ["통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스"]
//        }
//        */
//        ObjectNode objectNode = objectMapper.createObjectNode();
//        objectNode.put("name","맥도날드 슈비버거");
//        objectNode.put("price",5000);
//        ArrayNode arrayNode = objectMapper.createArrayNode();
//        arrayNode.add("통새우 패티");
//        arrayNode.add("순쇠고기 패티");
//        arrayNode.add("토마토");
//        arrayNode.add("스파이시 어니언 소스");
//        objectNode.set("ingredients",arrayNode);
//        String json = objectNode.toString();
//
//        // 수행
//        Bugger bugger = objectMapper.readValue(json, Bugger.class);
//
//        // 예상
//        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
//        Bugger expected = new Bugger("맥도날드 슈비버거", 5000, ingredients);
//        // 검증
//        assertEquals(expected.toString(),bugger.toString());
//        JsonNode jsonNode = objectMapper.readTree(json);
//        System.out.println(jsonNode.toPrettyString());
//        System.out.println(bugger.toString());
//    }
//
//}