package br.edu.uni7.questoes1e3.model.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.bind.adapter.JsonbAdapter;

public class DateAdapter implements JsonbAdapter<Date, String> {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String adaptToJson(Date value) throws Exception {
		if (value == null) {
			return "";
		}
		return sdf.format(value);
	}

	@Override
	public Date adaptFromJson(String value) throws Exception {
		if (value == null || value.trim().equals("")) {
			return null;
		}
		return sdf.parse(value);
	}

}