package com.smhrd.ttok.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.smhrd.ttok.DTO.request.user.ChatKoRequestDTO;
import com.smhrd.ttok.DTO.request.user.ChatEnRequestDTO;
import com.smhrd.ttok.domain.Chatting_Ko;
import com.smhrd.ttok.domain.Chatting_En;
import com.smhrd.ttok.domain.User;
import com.smhrd.ttok.repository.ChattingKoRepository;
import com.smhrd.ttok.repository.ChattingEnRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ChattingService {
    private final ChattingKoRepository chattingKoRepository;
    private final ChattingEnRepository chattingEnRepository;

    public Map<Long, List<ChatKoRequestDTO>> chatTolistK(){
        List<Chatting_Ko> koList = chattingKoRepository.findAll();
        List<ChatKoRequestDTO> chatList = new ArrayList<>();

        Map<Long, List<ChatKoRequestDTO>> KoChatList = new HashMap<Long, List<ChatKoRequestDTO>>();
        for(Chatting_Ko ko : koList){
            ChatKoRequestDTO addChatKoRequestDTO = ChatKoRequestDTO.builder()
            .id(ko.getId())
            .question(ko.getQuestion())
            .answer(ko.getAnswer().getAnswer())
            .user(ko.getUser().getId())
            .time(ko.getTime())
            .build();

            chatList.add(addChatKoRequestDTO);
        }

        for(ChatKoRequestDTO dto : chatList){
            long UserIdx = dto.getUser();

            if(KoChatList.containsKey(UserIdx)){
                KoChatList.get(UserIdx).add(dto);
            }else{
                KoChatList.putIfAbsent(UserIdx, new ArrayList<>());
                KoChatList.get(UserIdx).add(dto); 
            }
        }
        return KoChatList;
    }
    
    public Map<Long, List<ChatEnRequestDTO>> chatTolistE(){
        List<Chatting_En> enList = chattingEnRepository.findAll();
        List<ChatEnRequestDTO> chatList = new ArrayList<>();

        Map<Long, List<ChatEnRequestDTO>> EnChatList = new HashMap<Long, List<ChatEnRequestDTO>>();
        for(Chatting_En en : enList){
            ChatEnRequestDTO addChatEnRequestDTO = ChatEnRequestDTO.builder()
            .id(en.getId())
            .question(en.getQuestion())
            .answer(en.getAnswer().getAnswer())
            .user(en.getUser().getId())
            .time(en.getTime())
            .build();

            chatList.add(addChatEnRequestDTO);
        }

        for(ChatEnRequestDTO dto : chatList){
            long UserIdx = dto.getUser();

            if(EnChatList.containsKey(UserIdx)){
                EnChatList.get(UserIdx).add(dto);
            }else{
                EnChatList.putIfAbsent(UserIdx, new ArrayList<>());
                EnChatList.get(UserIdx).add(dto); 
            }
        }
        return EnChatList;
    }
}
