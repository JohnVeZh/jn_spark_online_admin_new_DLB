package com.business.v2.Tools;

import java.io.File;
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

import com.business.v2.question.TbQuestionListening;
import com.business.v2.question.TbQuestionListeningQuestion;
import com.business.v2.question.TbQuestionListeningQuestionOption;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbSpecialTraining;
import com.easecom.common.framework.hibernate.BaseModel;

public class Listening {
	/*public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.easecom.common.framework.hibernate.HibernateSessionFactory");
		System.out.println("--- 构建SessionFactory完成 ---");
		
		Listening il = new Listening();
		File file = new File("C:\\Users\\spark\\Desktop\\专项题库准备\\六级\\听力——简·六级听力.xls");
//		File file = new File("C:\\Users\\spark\\Desktop\\专项题库准备\\四级\\四级听力10套模板导入-3.31.xls");
		Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> typeMap = il.readFile(file);
		System.out.println("---- 读取Excel结束 ----");
		
		List<BaseModel> list = il.parseData(typeMap);
		System.out.println("---- 加工数据完毕 ----");
		
		TT.save(list);
		System.out.println("---- 持久化数据结束 ----");
		
		System.out.println(list.size());
	}*/
	
	public Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> readFile(InputStream file) {
		//		分类				目录列表		目录				片段列表	片段					题目列表		题目							选项列表
		Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> typeMap = new HashMap<TbSpecialCatalog, List<Map<TbSpecialCatalog,List<Map<TbQuestionListening,List<Map<TbQuestionListeningQuestion,List<TbQuestionListeningQuestionOption>>>>>>>>();
		
		HSSFSheet sheet = TT.getSheet(file, 0);
		int rows = sheet.getPhysicalNumberOfRows();
		
		TbSpecialCatalog type = null;	// 当前分类
		// 片段列表
		List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>> listenList = null;
		// 题目列表
		List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>> questionList = null;
		// 选项列表
		List<TbQuestionListeningQuestionOption> optionList = null;
		
		for(int i=2; i<rows; i++) {
			HSSFRow row = sheet.getRow(i);
			if(row==null || TT.value(row.getCell(12))==null) {
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
					List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>> catList = new ArrayList<>();
					typeMap.put(type, catList);
				}
			}
			String catName = TT.value(row.getCell(2));
			if(StringUtils.isNotBlank(catName)) {
				TbSpecialCatalog cat = new TbSpecialCatalog();
				cat.setName(catName);
				cat.setSortOrder(TT.valueInt(row.getCell(7)));
				listenList = new ArrayList<>();
				Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>> catMap = new HashMap<>();
				catMap.put(cat, listenList);
				typeMap.get(type).add(catMap);
			}
			String listenTitle = TT.value(row.getCell(3));
			if(StringUtils.isNotBlank(listenTitle)) {
				TbQuestionListening listen = new TbQuestionListening();
				listen.setTitle(listenTitle);
				listen.setAudioUrl(TT.value(row.getCell(4)));
				listen.setTapescripts(TT.value(row.getCell(5)));
				listen.setTranslation(TT.value(row.getCell(6)));
				listen.setQuestionQuantity(TT.valueInt(row.getCell(8)));
				questionList = new ArrayList<>();
				Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>> listenMap = new HashMap<>();
				listenMap.put(listen, questionList);
				listenList.add(listenMap);
			}
			int questionSort = TT.valueInt(row.getCell(9));
			if(questionSort != -1) {
				TbQuestionListeningQuestion question = new TbQuestionListeningQuestion();
				question.setTitle(TT.value(row.getCell(10)));
				question.setAnalysis(TT.value(row.getCell(11)));
				question.setSortOrder(questionSort);
				Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>> questionMap = new HashMap<>();
				optionList = new ArrayList<>();
				questionMap.put(question, optionList);
				questionList.add(questionMap);
			}
			String optionPrefix = TT.value(row.getCell(12));
			if(StringUtils.isNotBlank(optionPrefix)) {
				TbQuestionListeningQuestionOption option = new TbQuestionListeningQuestionOption();
				option.setContent(TT.value(row.getCell(13)));
				option.setIsAnswer(TT.valueBoolean(row.getCell(14)) ? "1" : "0");
				optionList.add(option);
			}
		}
		
		return typeMap;
	}
	
	public List<BaseModel> parseData(Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> typeMap) {
		List<BaseModel> list = new ArrayList<BaseModel>();
		for(Map.Entry<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> typeItem : typeMap.entrySet()) {
			TbSpecialCatalog type = typeItem.getKey();
			String section = type.getId();
			String typeId = getTypeIdBySection(section, type.getName());
			List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>> catList = typeItem.getValue();
			for(int i=0; i<catList.size(); i++) {
				Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>> catMap = catList.get(i);
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
					List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>> listenList = catMap.get(cat);
					for(int j=0; j<listenList.size(); j++) {
						Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>> listenMap = listenList.get(j);
						for(Map.Entry<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>> listenItem : listenMap.entrySet()) {
							TbQuestionListening listen = listenItem.getKey();
							String trainingId = TT.uuid();
							listen.setId(trainingId);
							listen.setTarget("1");
							listen.setIsDel("0");
							listen.setCreateTime(new Date());
							list.add(listen);
							TbSpecialTraining train = new TbSpecialTraining(TT.uuid());
							train.setSection(section);
							train.setCatalogId(catId);
							train.setTrainingType("1");
							train.setTrainingId(trainingId);
							train.setSortOrder(TT.nextTrainOrder(catId));
							list.add(train);
							List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>> questionList = listenItem.getValue();
							for(int k=0; k<questionList.size(); k++) {
								Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>> questionMap = questionList.get(k);
								for(Map.Entry<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>> questionItem : questionMap.entrySet()) {
									TbQuestionListeningQuestion question = questionItem.getKey();
									String questionId = TT.uuid();
									question.setId(questionId);
									question.setListeningId(trainingId);
									list.add(question);
									List<TbQuestionListeningQuestionOption> optionList = questionItem.getValue();
									for(TbQuestionListeningQuestionOption option : optionList) {
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

	public List<BaseModel> parseData2(Map<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> typeMap) {
		List<BaseModel> list = new ArrayList<BaseModel>();
		for(Map.Entry<TbSpecialCatalog, List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>>> typeItem : typeMap.entrySet()) {
			TbSpecialCatalog type = typeItem.getKey();
			String section = type.getId();
			String typeId = getTypeIdBySection(section, type.getName());
			List<Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>>> catList = typeItem.getValue();
			for(int i=0; i<catList.size(); i++) {
				Map<TbSpecialCatalog, List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>>> catMap = catList.get(i);
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
					List<Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>>> listenList = catMap.get(cat);
					for(int j=0; j<listenList.size(); j++) {
						Map<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>> listenMap = listenList.get(j);
						for(Map.Entry<TbQuestionListening, List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>>> listenItem : listenMap.entrySet()) {
							TbQuestionListening listen = listenItem.getKey();
							String trainingId = TT.uuid();
							listen.setId(trainingId);
							listen.setTarget("2");
							listen.setIsDel("0");
							listen.setCreateTime(new Date());
							list.add(listen);
							TbSpecialTraining train = new TbSpecialTraining(TT.uuid());
							train.setSection(section);
							train.setCatalogId(catId);
							train.setTrainingType("1");
							train.setTrainingId(trainingId);
							train.setSortOrder(TT.nextTrainOrder(catId));
							list.add(train);
							List<Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>>> questionList = listenItem.getValue();
							for(int k=0; k<questionList.size(); k++) {
								Map<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>> questionMap = questionList.get(k);
								for(Map.Entry<TbQuestionListeningQuestion, List<TbQuestionListeningQuestionOption>> questionItem : questionMap.entrySet()) {
									TbQuestionListeningQuestion question = questionItem.getKey();
									String questionId = TT.uuid();
									question.setId(questionId);
									question.setListeningId(trainingId);
									list.add(question);
									List<TbQuestionListeningQuestionOption> optionList = questionItem.getValue();
									for(TbQuestionListeningQuestionOption option : optionList) {
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
	
//	private String getNameByCode(String code) {
//		String name = null;
//		if("01".equals(code)) {
//			name = "短篇新闻";
//		} else if("02".equals(code)) {
//			name = "长对话";
//		} else if("03".equals(code)) {
//			name = "短文理解";
//		} else if("04".equals(code)) {
//			name = "讲座讲话";
//		}
//		return name;
//	}
	
	private String getTypeIdBySection(String section, String code) {
		String id = "";
		if("01".equals(section) && "01".equals(code)) {
			id = "010201";
		} else if("01".equals(section) && "02".equals(code)) {
			id = "010202";
		} else if("01".equals(section) && "03".equals(code)) {
			id = "010203";
		} else if("02".equals(section) && "02".equals(code)) {
			id = "020201";
		} else if("02".equals(section) && "03".equals(code)) {
			id = "020202";
		} else if("02".equals(section) && "04".equals(code)) {
			id = "020203";
		}
		return id;
	}
}
