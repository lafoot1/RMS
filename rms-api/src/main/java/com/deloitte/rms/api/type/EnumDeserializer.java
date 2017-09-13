package com.deloitte.rms.api.type;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class EnumDeserializer {
	public static class InterviewTypeDeserializer extends JsonDeserializer<InterviewType> {
		@Override
		public InterviewType deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
			return InterviewType.getByDesc(node.asText());
		}
	}
	public class PersonTypeDeserializer extends JsonDeserializer<PersonType> {
		@Override
		public PersonType deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return PersonType.getByCode(node.asText());
		}
	}
	public class ContentTypeDeserializer extends JsonDeserializer<ContentType> {
		@Override
		public ContentType deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return ContentType.getByType(node.asText());
		}
	}
	public class InterviewModeTypeDeserializer extends JsonDeserializer<InterviewModeType> {
		@Override
		public InterviewModeType deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return InterviewModeType.getByCode(node.asText());
		}
	}
	public class SpecialityTypeDeserializer extends JsonDeserializer<SpecialityType> {
		@Override
		public SpecialityType deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return SpecialityType.getByCode(node.asText());
		}
	}
	public class FunctionTypeDeserializer extends JsonDeserializer<FunctionType> {
		@Override
		public FunctionType deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return FunctionType.getByCode(node.asText());
		}
	}
	public class ReferralStatusDeserializer extends JsonDeserializer<ReferralStatus> {
		@Override
		public ReferralStatus deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return ReferralStatus.getByCode(node.asText());
		}
	}
	public class ReferralSubstatusDeserializer extends JsonDeserializer<ReferralSubstatus> {
		@Override
		public ReferralSubstatus deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return ReferralSubstatus.getByCode(node.asText());
		}
	}
	public class GenderTypeDeserializer extends JsonDeserializer<GenderType> {
		@Override
		public GenderType deserialize(JsonParser jp, DeserializationContext ctx)
				throws IOException, JsonProcessingException {
			JsonNode node = jp.getCodec().readTree(jp);
	        return GenderType.getByCode(node.asText());
		}
	}
}
