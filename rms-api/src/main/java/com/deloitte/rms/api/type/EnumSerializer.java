package com.deloitte.rms.api.type;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class EnumSerializer {
	public static class ReferralStatusSerializer extends JsonSerializer<ReferralStatus> {
		@Override
		public void serialize(ReferralStatus value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			gen.writeString(value.getCode());
		}
	}
	public static class ReferralSubstatusSerializer extends JsonSerializer<ReferralSubstatus> {
		@Override
		public void serialize(ReferralSubstatus value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			gen.writeString(value.getCode());
		}
	}
	public static class GenderTypeSerializer extends JsonSerializer<GenderType> {
		@Override
		public void serialize(GenderType value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			gen.writeString(value.getCode());
		}
	}
	public static class FunctionTypeSerializer extends JsonSerializer<FunctionType> {
		@Override
		public void serialize(FunctionType value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			gen.writeString(value.getCode());
		}
	}
}
