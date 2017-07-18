package com.business.v2.Tools;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.business.v2.question.TbQuestionReading;
import com.business.v2.question.TbQuestionReadingQuestion;
import com.business.v2.question.TbQuestionReadingQuestionOption;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbSpecialTraining;
import com.easecom.common.framework.hibernate.BaseModel;

public class Reading {
/*	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.easecom.common.framework.hibernate.HibernateSessionFactory");
		System.out.println("--- 构建SessionFactory完成 ---");
		
		Reading il = new Reading();
		File file = new File("C:\\Users\\spark\\Desktop\\6r.xls");
		Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> typeMap = il.readFile(file);
		System.out.println("---- 读取Excel结束 ----");

		List<BaseModel> list = il.parseData(typeMap);
		System.out.println("---- 加工数据完毕 ----");
		
		List<BaseModel> list = new ArrayList<>();
		File[] files = new File("C:\\Users\\spark\\Desktop\\专项题库准备\\六级").listFiles(new FilenameFilter() {
		File[] files = new File("C:\\Users\\spark\\Desktop\\专项题库准备\\四级").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.indexOf("阅读")!=-1;
			}
		});
		for(File file : files) {
			list.addAll(il.parseData(il.readFile(file)));
		}
		System.out.println("---- 读取并加工数据完毕 ----");
		
		TT.save(list);
		System.out.println("---- 持久化数据结束 ----");
		
		System.out.println(list.size());
	}*/
	
	public Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> readFile(InputStream file) {
		//		分类				目录列表		目录				片段列表	片段					题目列表		题目							选项列表
		Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> typeMap = new HashMap<TbSpecialCatalog, List<Map<TbSpecialCatalog,List<Map<TbQuestionReading,List<Map<TbQuestionReadingQuestion,List<TbQuestionReadingQuestionOption>>>>>>>>();
		
		HSSFSheet sheet = TT.getSheet(file, 0);
		int rows = sheet.getPhysicalNumberOfRows();
		
		TbSpecialCatalog type = null;	// 当前分类
		// 片段列表
		List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>> readList = null;
		// 题目列表
		List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>> questionList = null;
		// 选项列表
		List<TbQuestionReadingQuestionOption> optionList = null;
		
		for(int i=2; i<rows; i++) {
			HSSFRow row = sheet.getRow(i);
			if(row==null || TT.value(row.getCell(11))==null) {
				continue;
			}
			String typeCode = TT.value(row.getCell(1));
			String id = TT.value(row.getCell(0));
			if(StringUtils.isNotBlank(typeCode)) {
				type = null;
				for(TbSpecialCatalog t : typeMap.keySet()) {
					if(t.getName().equals(typeCode) && t.getId().equals(id)) {
						type = t;
						break;
					}
				}
				if(type == null) {
					type = new TbSpecialCatalog();
					type.setId(TT.value(row.getCell(0)));
					type.setName(typeCode);
					List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>> catList = new ArrayList<>();
					typeMap.put(type, catList);
				}
			}
			String catName = TT.value(row.getCell(2));
			if(StringUtils.isNotBlank(catName)) {
				TbSpecialCatalog cat = new TbSpecialCatalog();
				cat.setName(catName);
				cat.setSortOrder(TT.valueInt(row.getCell(6)));
				readList = new ArrayList<>();
				Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>> catMap = new HashMap<>();
				catMap.put(cat, readList);
				typeMap.get(type).add(catMap);
			}
			String readContent = TT.value(row.getCell(4));
			if(StringUtils.isNotBlank(readContent)) {
				TbQuestionReading read = new TbQuestionReading();
				read.setTitle(TT.value(row.getCell(3)));
				read.setContent(readContent);
				read.setTranslation(TT.value(row.getCell(5)));
				read.setQuestionQuantity(TT.valueInt(row.getCell(7)));
				questionList = new ArrayList<>();
				Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>> readMap = new HashMap<>();
				readMap.put(read, questionList);
				readList.add(readMap);
			}
			int questionSort = TT.valueInt(row.getCell(8));
			if(questionSort != -1) {
				TbQuestionReadingQuestion question = new TbQuestionReadingQuestion();
				question.setTitle(TT.value(row.getCell(9)));
				question.setAnalysis(TT.value(row.getCell(10)));
				question.setSortOrder(questionSort);
				Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>> questionMap = new HashMap<>();
				optionList = new ArrayList<>();
				questionMap.put(question, optionList);
				questionList.add(questionMap);
			}
			String optionPrefix = TT.value(row.getCell(11));
			if(StringUtils.isNotBlank(optionPrefix)) {
				TbQuestionReadingQuestionOption option = new TbQuestionReadingQuestionOption();
				option.setContent(TT.value(row.getCell(12)));
				option.setIsAnswer(TT.valueBoolean(row.getCell(13)) ? "1" : "0");
				optionList.add(option);
			}
		}
		
		return typeMap;
	}
	
	public List<BaseModel> parseData(Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> typeMap) {
		List<BaseModel> list = new ArrayList<BaseModel>();
		for(Map.Entry<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> typeItem : typeMap.entrySet()) {
			TbSpecialCatalog type = typeItem.getKey();
			String section = type.getId();
			String typeId = getTypeIdBySection(section, type.getName());
			List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>> catList = typeItem.getValue();
			for(int i=0; i<catList.size(); i++) {
				Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>> catMap = catList.get(i);
				List<TbSpecialCatalog> catList1 = new ArrayList<TbSpecialCatalog>(catMap.keySet());
				Collections.sort(catList1, new Comparator<TbSpecialCatalog>() {
					public int compare(TbSpecialCatalog o1, TbSpecialCatalog o2) {
						return o1.getSortOrder()>o2.getSortOrder() ? 1 : -1;
					}
				});
				for(TbSpecialCatalog cat : catList1) {
					String catId = TT.nextCatId(typeId, 6);
					cat.setId(catId);
					cat.setPId(typeId);
					if(TT.INTERNAL_SORT) {
						cat.setSortOrder(TT.nextCatOrder(typeId));
					}
					cat.setIsDel("0");
					cat.setCreateTime(new Date());
					list.add(cat);
					List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>> readList = catMap.get(cat);
					for(int j=0; j<readList.size(); j++) {
						Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>> readMap = readList.get(j);
						for(Map.Entry<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>> readItem : readMap.entrySet()) {
							TbQuestionReading read = readItem.getKey();
							String trainingId = TT.uuid();
							read.setId(trainingId);
							read.setTarget("1");
							read.setIsDel("0");
							read.setCreateTime(new Date());
							list.add(read);
							TbSpecialTraining train = new TbSpecialTraining(TT.uuid());
							train.setSection(section);
							train.setCatalogId(catId);
							train.setTrainingType("2");
							train.setTrainingId(trainingId);
							train.setSortOrder(TT.nextTrainOrder(catId));
							list.add(train);
							List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>> questionList = readItem.getValue();
							for(int k=0; k<questionList.size(); k++) {
								Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>> questionMap = questionList.get(k);
								for(Map.Entry<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>> questionItem : questionMap.entrySet()) {
									TbQuestionReadingQuestion question = questionItem.getKey();
									String questionId = TT.uuid();
									question.setId(questionId);
									question.setReadingId(trainingId);
									list.add(question);
									List<TbQuestionReadingQuestionOption> optionList = questionItem.getValue();
									for(TbQuestionReadingQuestionOption option : optionList) {
										option.setId(TT.uuid());
										option.setQuestionId(questionId);
										list.add(option);
									}
								}
							}
						}
					}
				}
			}
		}
		return list;
	}

	public List<BaseModel> parseData2(Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> typeMap) {
		List<BaseModel> list = new ArrayList<BaseModel>();
		for(Map.Entry<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>>> typeItem : typeMap.entrySet()) {
			TbSpecialCatalog type = typeItem.getKey();
			String section = type.getId();
			String typeId = getTypeIdBySection(section, type.getName());
			List<Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>>> catList = typeItem.getValue();
			for(int i=0; i<catList.size(); i++) {
				Map<TbSpecialCatalog, List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>>> catMap = catList.get(i);
				List<TbSpecialCatalog> catList1 = new ArrayList<TbSpecialCatalog>(catMap.keySet());
				Collections.sort(catList1, new Comparator<TbSpecialCatalog>() {
					public int compare(TbSpecialCatalog o1, TbSpecialCatalog o2) {
						return o1.getSortOrder()>o2.getSortOrder() ? 1 : -1;
					}
				});
				for(TbSpecialCatalog cat : catList1) {
					String catId = TT.nextCatId(typeId, 6);
					cat.setId(catId);
					cat.setPId(typeId);
					if(TT.INTERNAL_SORT) {
						cat.setSortOrder(TT.nextCatOrder(typeId));
					}
					cat.setIsDel("0");
					cat.setCreateTime(new Date());
					list.add(cat);
					List<Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>>> readList = catMap.get(cat);
					for(int j=0; j<readList.size(); j++) {
						Map<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>> readMap = readList.get(j);
						for(Map.Entry<TbQuestionReading, List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>>> readItem : readMap.entrySet()) {
							TbQuestionReading read = readItem.getKey();
							String trainingId = TT.uuid();
							read.setId(trainingId);
							read.setTarget("2");
							read.setIsDel("0");
							read.setCreateTime(new Date());
							list.add(read);
							TbSpecialTraining train = new TbSpecialTraining(TT.uuid());
							train.setSection(section);
							train.setCatalogId(catId);
							train.setTrainingType("2");
							train.setTrainingId(trainingId);
							train.setSortOrder(TT.nextTrainOrder(catId));
							list.add(train);
							List<Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>>> questionList = readItem.getValue();
							for(int k=0; k<questionList.size(); k++) {
								Map<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>> questionMap = questionList.get(k);
								for(Map.Entry<TbQuestionReadingQuestion, List<TbQuestionReadingQuestionOption>> questionItem : questionMap.entrySet()) {
									TbQuestionReadingQuestion question = questionItem.getKey();
									String questionId = TT.uuid();
									question.setId(questionId);
									question.setReadingId(trainingId);
									list.add(question);
									List<TbQuestionReadingQuestionOption> optionList = questionItem.getValue();
									for(TbQuestionReadingQuestionOption option : optionList) {
										option.setId(TT.uuid());
										option.setQuestionId(questionId);
										list.add(option);
									}
								}
							}
						}
					}
				}
			}
		}
		return list;
	}
	
	private String getTypeIdBySection(String section, String code) {

		String id = "";
		if("01".equals(section) && "01".equals(code)) {
			id = "010301";
		} else if("01".equals(section) && "02".equals(code)) {
			id = "010302";
		} else if("01".equals(section) && "03".equals(code)) {
			id = "010303";
		} else if("02".equals(section) && "01".equals(code)) {
			id = "020301";
		} else if("02".equals(section) && "02".equals(code)) {
			id = "020302";
		} else if("02".equals(section) && "03".equals(code)) {
			id = "020303";
		}
		return id;
	}
}
