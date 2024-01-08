package org.finos.ls.chatgpt;

import java.util.Arrays;

import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.Message;

@Service
public class ChatGPTService implements InitializingBean {

	@Value("${openai.key}")
	String apiKey;
	
	ChatGPT gpt;
	
	Message prolog;
	
	public String makeRequest(String req) {
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT_4.getName())
                .messages(Arrays.asList(prolog, Message.of(req)))
                .build();        
        
		 String res = gpt.chatCompletion(chatCompletion).getChoices().get(0).getMessage().getContent();
	     return res;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		gpt = ChatGPT.builder()
                .apiKey(apiKey)
                .build()
                .init();

		this.prolog = Message.of(StreamUtils.copyToString(this.getClass().getResourceAsStream("/prolog.md"), Charsets.UTF_8));

	}
}
