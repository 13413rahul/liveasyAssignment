package com.livesy.internship.service;

import com.livesy.internship.request.InsertLoadRequest;
import com.livesy.internship.request.UpdateLoadRequest;
import com.livesy.internship.response.LoadIdResponse;
import com.livesy.internship.response.LoadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoadService {

    ResponseEntity<String> registerPayload(InsertLoadRequest insertLoadRequest);

    ResponseEntity<List<LoadResponse>> getLoadByShipperId(String shipperId);

    ResponseEntity<LoadIdResponse> getLoadWithLoadId(Long loadId);

    ResponseEntity<String> updateLoadWithLoadId(Long loadId, UpdateLoadRequest updateLoadRequest);

    ResponseEntity<String> deleteLoadWithLoadId(Long loadId);
}
