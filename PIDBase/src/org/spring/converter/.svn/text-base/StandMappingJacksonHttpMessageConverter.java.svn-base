package org.spring.converter;

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;



public class StandMappingJacksonHttpMessageConverter extends MappingJacksonHttpMessageConverter {
	
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		ObjectMapper om=this.getObjectMapper();
		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator jsonGenerator =
			om.getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);

		// A workaround for JsonGenerators not applying serialization features
		// https://github.com/FasterXML/jackson-databind/issues/12
		if (om.getSerializationConfig().isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
			jsonGenerator.useDefaultPrettyPrinter();
		}

		try {
			CallBack obj=(CallBack)object;
			boolean falg=null!=obj.getCallback()&&!obj.getCallback().equals("");
			if (falg) {
				jsonGenerator.writeRaw(obj.getCallback()+"(");
				//jsonGenerator.writeEndObject();
			}
			
			om.writeValue(jsonGenerator, object);
			if (falg) {				
				jsonGenerator.writeRaw(")");
				jsonGenerator.flush();
				}
			
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}

}
