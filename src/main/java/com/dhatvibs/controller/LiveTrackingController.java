
  package com.dhatvibs.controller;
  
  import com.dhatvibs.dto.RiderLocationDto;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.messaging.handler.annotation.MessageMapping; import
  org.springframework.messaging.simp.SimpMessagingTemplate; import
  org.springframework.stereotype.Controller;
  
  @Controller public class LiveTrackingController {
  
  @Autowired private SimpMessagingTemplate messagingTemplate;
  
  @MessageMapping("/rider/location") public void
  receiveLocation(RiderLocationDto dto) { messagingTemplate.convertAndSend(
  "/topic/order/" + dto.getOrderId(), dto ); } }
  
  

