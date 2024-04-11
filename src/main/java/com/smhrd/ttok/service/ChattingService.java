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
            .phone(ko.getUser().getPhone())
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
            .phone(en.getUser().getPhone())
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

    public Map<String, List<ChatKoRequestDTO>> chatLogKo(){
        List<Chatting_Ko> koList = chattingKoRepository.findAll();
        List<ChatKoRequestDTO> chatList = new ArrayList<>();

        Map<String, List<ChatKoRequestDTO>> KoChatList = new HashMap<String, List<ChatKoRequestDTO>>();
        for(Chatting_Ko ko : koList){
            ChatKoRequestDTO addChatKoRequestDTO = ChatKoRequestDTO.builder()
            .id(ko.getId())
            .question(ko.getQuestion())
            .answer(ko.getAnswer().getAnswer())
            .user(ko.getUser().getId())
            .phone(ko.getUser().getPhone())
            .time(ko.getTime())
            .build();

            chatList.add(addChatKoRequestDTO);
        }

        for(ChatKoRequestDTO dto : chatList){
            String UserPhone = dto.getPhone();

            if(KoChatList.containsKey(UserPhone)){
                KoChatList.get(UserPhone).add(dto);
            }else{
                KoChatList.putIfAbsent(UserPhone, new ArrayList<>());
                KoChatList.get(UserPhone).add(dto); 
            }
        }
        return KoChatList;
    }

    public Map<String, List<ChatEnRequestDTO>> chatLogEn(){
        List<Chatting_En> enList = chattingEnRepository.findAll();
        List<ChatEnRequestDTO> chatList = new ArrayList<>();

        Map<String, List<ChatEnRequestDTO>> EnChatList = new HashMap<String, List<ChatEnRequestDTO>>();
        for(Chatting_En en : enList){
            ChatEnRequestDTO addChatEnRequestDTO = ChatEnRequestDTO.builder()
            .id(en.getId())
            .question(en.getQuestion())
            .answer(en.getAnswer().getAnswer())
            .user(en.getUser().getId())
            .phone(en.getUser().getPhone())
            .time(en.getTime())
            .build();

            chatList.add(addChatEnRequestDTO);
        }

        for(ChatEnRequestDTO dto : chatList){
            String UserPhone = dto.getPhone();

            if(EnChatList.containsKey(UserPhone)){
                EnChatList.get(UserPhone).add(dto);
            }else{
                EnChatList.putIfAbsent(UserPhone, new ArrayList<>());
                EnChatList.get(UserPhone).add(dto); 
            }
        }
        return EnChatList;
    }

    //미해결 질문 처리를 위해 채팅로그에서 답변되지 않은 로그를 확인 후 리스팅. 2024.04.11 엄다은
    public Map<String, List<?>> Unsolving(){
        List<Chatting_Ko> koList = chattingKoRepository.findAll();
        List<ChatKoRequestDTO> chatListK = new ArrayList<>();

        String WrongAnswerK = "죄송합니다. 이해하지 못했습니다. 키워드로 질문하거나 좀 더 정확하게 말씀해주세요. 예 : 제품 등록";
        String WrongAnswerE = "I'm sorry. I didn't understand. Please ask me a keyword or tell me more accurately. Example : Product Registration";

        for (Chatting_Ko ko : koList) {
            if (ko.getAnswer().getAnswer().equals(WrongAnswerK)) {
                ChatKoRequestDTO dto = ChatKoRequestDTO.builder()
                        .id(ko.getId())
                        .question(ko.getQuestion())
                        .build();
                    chatListK.add(dto);
            }
        }

        List<Chatting_En> enList = chattingEnRepository.findAll();
        List<ChatEnRequestDTO> chatListE = new ArrayList<>();

        for (Chatting_En en : enList) {
            if (en.getAnswer().getAnswer().equals(WrongAnswerE)) {
                ChatEnRequestDTO dto = ChatEnRequestDTO.builder()
                        .id(en.getId())
                        .question(en.getQuestion())
                        .build();
                chatListE.add(dto);
            }
        }
        Map<String, List<?>> result = new HashMap<>();
        result.put("chatListK", chatListK);
        result.put("chatListE", chatListE);
        return result;
    }
}
