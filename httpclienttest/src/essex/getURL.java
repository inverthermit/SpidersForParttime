package essex;

public class getURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String[][] PostData={
		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N40012","MSc Accounting","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N42012","MSc Accounting and Finance","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++W41012","MA Acting","1 year","Full-time","East 15 Acting School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G40012","MSc Advanced Computer Science","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+A40136","MSc Advanced Periodontal Practice","3 years","Part-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C80712","MSc Advanced Psychology","1 year","Full-time","Psychology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++V35012","MA Art History and Theory","1 year","Full-time","Philosophy and Art History (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N31012","MSc Banking and Finance","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MBA+N20012","MBA Business Administration","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N11112","MSc Business Analytics","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N30312","MSc Computational Finance","1 year","Full-time","Computational Finance and Economic Agents"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G61012","MSc Computer Games","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++W80012","MA Creative Writing","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L30812","MSc Criminology and Socio-Legal Research","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G30412","MSc Data Science","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L10012","MSc Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+H61012","MSc Electronic Engineering","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q16012","MA English Language and Linguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N10012","MSc Entrepreneurship and Innovation","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+D44012","MSc Environment and Resource Management","1 year","Full-time","Biological Sciences (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++QV2312","MA Film Studies","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N30012","MSc Finance","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N3G312","MSc Finance and Data Analytics","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N35112","MSc Finance and Global Trading","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L11112","MSc Financial Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++V10012","MA History","1 year","Full-time","History"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L25012","MA International Relations","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L25012","MSc International Relations","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q10012","MA Linguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q20012","MA Literature","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N20012","MSc Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L12012","MSc Money and Banking","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+B74024","MSc Nursing (Adult) (Pre-Registration)","2 years","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+B76024","MSc Nursing (Mental Health) (Pre-Registration)","2 years","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+B93024","MSc Occupational Therapy (Pre-Registration)","2 years","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+A40036","MSc Periodontology","3 years","Part-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++V50012","MA Philosophy","1 year","Full-time","Philosophy and Art History (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+B16024","MSc Physiotherapy (Pre-Registration)","2 years","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L20012","MA Political Science","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L20012","MSc Political Science","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L20112","MA Politics","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++C89012","MA Psychoanalytic Studies","1 year","Full-time","Psychoanalytic Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C80012","MSc Psychology","1 year","Full-time","Psychology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L30012","MA Sociology","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+B62024","MSc Speech and Language Therapy (Pre-Registration)","2 years","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G30012","MSc Statistics","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L3MV12","MA Theory and Practice of Human Rights","1 year","Full-time","Human Rights"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C16112","MSc Tropical Marine Biology","1 year","Full-time","Biological Sciences (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++W8F912","MA Wild Writing: Literature and the Environment","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N4N312","MSc Accounting and Financial Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESN40212","MRes Accounting and Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MFA+W41020","MFA Acting","1 year","Full-time","East 15 Acting School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MFA+W41220","MFA Acting (International)","1 year","Full-time","East 15 Acting School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++W41212","MA Acting (International)","1 year","Full-time","East 15 Acting School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G40712","MSc Advanced Web Engineering","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++NP5312","MA Advertising, Marketing and the Media","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N35012","MSc Algorithmic Trading","1 year","Full-time","Computational Finance and Economic Agents"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++T72012","MA American Literatures","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESQ14512","MRes Analysing Language Use","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L11012","MSc Applied Economics and Data Analysis","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q11012","MA Applied Linguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLV30009","Graduate Diploma Art History and Theory","9 months","Full-time","Philosophy and Art History (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G41112","MSc Artificial Intelligence","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L11912","MSc Behavioural Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G51512","MSc Big Data and Text Analytics","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C56012","MSc Biotechnology","1 year","Full-time","Biological Sciences (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++T1Q912","MA Chinese-English Translation and Interpreting","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+T1Q909","PG Diploma Chinese-English Translation and Interpreting","9 months","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G42512","MSc Cloud Computing","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C86212","MSc Cognitive Neuropsychology","1 year","Full-time","Psychology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C85012","MSc Cognitive Neuroscience","1 year","Full-time","Psychology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+H61212","MSc Computer Engineering","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+H60112","MSc Computer Networks and Security","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L25212","MA Conflict Resolution","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L25212","MSc Conflict Resolution","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G10112","MSc Discrete Mathematics and its Applications","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLM+M2M012","LLM Economic, Social and Cultural Rights","1 year","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLL10009","Graduate Diploma Economics","9 months","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESL10012","MRes Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L10112","MSc Economics and Econometrics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G40912","MSc Embedded Systems","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+M12009","PG Diploma European Union Commercial Law","9 months","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLM+M12012","LLM European Union Commercial Law","1 year","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESQ10412","MRes Experimental Linguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q20212","MA Film and Literature","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N39012","MSc Finance and Investment","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N30212","MSc Finance and Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L10312","MSc Financial and Business Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N3G612","MSc Financial Computing","1 year","Full-time","Computational Finance and Economic Agents"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L11412","MSc Financial Econometrics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L10212","MSc Financial Economics and Econometrics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N34212","MSc Financial Engineering and Risk Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++V35612","MA Gallery Studies and Critical Curating","1 year","Full-time","Philosophy and Art History (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L24012","MA Global and Comparative Politics","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L24012","MSc Global and Comparative Politics","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N20912","MSc Global Project Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++B99112","MA Health and Organisational Research","1 year","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+B99012","MSc Health Research","1 year","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N60012","MSc Human Resource Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++M90112","MA Human Rights and Cultural Diversity","1 year","Full-time","Human Rights"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L20212","MA Ideology and Discourse Analysis","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G40812","MSc Intelligent Systems and Robotics","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N40412","MSc International Accounting","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N12012","MSc International Business and Entrepreneurship","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLM+M2M212","LLM International Commercial and Business Law","1 year","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L16012","MSc International Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N30112","MSc International Finance","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLM+M10512","LLM International Human Rights and Humanitarian Law","1 year","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLM+M10112","LLM International Human Rights Law","1 year","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N20112","MSc International Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N55012","MSc International Marketing and Entrepreneurship","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESL25024","MRes International Relations","2 years","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLM+M10212","LLM International Trade Law","1 year","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++C89312","MA Jungian and Post-Jungian Studies","1 year","Full-time","Psychoanalytic Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q10812","MA Linguistic Studies","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESQ10012","MRes Linguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++C8N212","MA Management and Organisational Dynamics","1 year","Full-time","Psychoanalytic Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+C8N209","PG Diploma Management and Organisational Dynamics","9 months","Full-time","Psychoanalytic Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L10412","MSc Management Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N51012","MSc Marketing and Brand Management","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N50312","MSc Marketing Managementhttp://www.sx.ac.uk/coursefinder/a> (Subject to Approval)","1 year","Full-time","Essex Business School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLG10009","Graduate Diploma Mathematics","9 months","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C74112","MSc Molecular Medicine","1 year","Full-time","Biological Sciences (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++R9L212","MA Multilevel Governance in Europe","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+R9L212","MSc Multilevel Governance in Europe","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+MF9012","MSc Organised Crime, Terrorism and Security","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++W81112","MA Playwriting","1 year","Full-time","Literature, Film, and Theatre Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESL20624","MRes Political Economy","2 years","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L20612","MA Political Economy","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L20612","MSc Political Economy","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MRESL20024","MRes Political Science","2 years","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L20512","MA Political Theory","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLL20009","Graduate Diploma Politics","9 months","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLC89D09","Graduate Diploma Psychodynamic Approaches","9 months","Full-time","Psychoanalytic Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q15012","MA Psycholinguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=CER+B94009","PG Certificate Psychological Well-Being Practitioner (Low Intensity)","9 months","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=CERLB94009","Graduate Certificate Psychological Well-Being Practitioner (Low Intensity)","9 months","Full-time","Health and Human Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L20712","MA Public Opinion and Political Behaviour","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L20712","MSc Public Opinion and Political Behaviour","1 year","Full-time","Government"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++C89812","MA Refugee Care","1 year","Full-time","Psychoanalytic Studies"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+C80612","MSc Research Methods in Psychology","1 year","Full-time","Psychology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q14012","MA Sociolinguistics","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L30112","MA Sociological Research","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=CERLL30009","Graduate Certificate Sociology","9 months","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+G3L109","PG Diploma Statistics and Econometrics","9 months","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G3L112","MSc Statistics and Econometrics","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G20312","MSc Statistics and Operational Research","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+G20109","PG Diploma Statistics and Operational Research","9 months","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+L31012","MSc Survey Methods for Social Research","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++QX1312","MA Teaching English as a Foreign Languaghttp://www.sx.ac.uk/coursefinder/Teaching English to Speakers of Other Languages (TEFhttp://www.sx.ac.uk/coursefinder/TESOL)","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+H64112","MSc Telecommunication and Information Systems","1 year","Full-time","Computer Science and Electronic Engineering (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++W42012","MA Theatre Directing","1 year","Full-time","East 15 Acting School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MFA+W42124","MFA Theatre Directing","2 years","Full-time","East 15 Acting School"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q2Q912","MA Translation and Literature","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++Q91012","MA Translation, Interpreting and Subtitling","1 year","Full-time","Language and Linguistics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+LN1412","MSc Accounting and Financial Economics","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3N409","Graduate Diploma Accounting with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ1Q309","Graduate Diploma Applied Linguistics with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3V309","Graduate Diploma Art History with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+N30612","MSc Computational Economics, Financial Markets and Policy","1 year","Full-time","Economics"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3G409","Graduate Diploma Computer Science with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+G10109","PG Diploma Discrete Mathematics and its Applications","9 months","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3L109","Graduate Diploma Economics with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3H609","Graduate Diploma Electronic Engineering with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3N109","Graduate Diploma Entrepreneurship and Innovation with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3M209","Graduate Diploma European Union Commercial Law with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3N309","Graduate Diploma Finance with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+G1N312","MSc Financial Decision Making with Applications","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=CER+V10009","PG Certificate History","9 months","Full-time","History"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3V109","Graduate Diploma History with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3M409","Graduate Diploma International Commercial and Business Law with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+M10209","PG Diploma International Trade Law","9 months","Full-time","Law (School of)"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3M509","Graduate Diploma International Trade Law with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3N209","Graduate Diploma Management with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+GN1309","PG Diploma Mathematics and Finance","9 months","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSC+GN1312","MSc Mathematics and Finance","1 year","Full-time","Mathematical Sciences"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3V509","Graduate Diploma Philosophy with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLQ3L209","Graduate Diploma Politics with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIPLC8Q309","Graduate Diploma Psychoanalytic Studies with English for Academic Purposes","9 months","Full-time","International Academy"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MA++L3N212","MA Sociology and Management","1 year","Full-time","Sociology"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DIP+G1N309","PG Diploma Financial Decision Making with Applications","9 months","Full-time","Mathematical Sciences"}};
	public static String[][] UnData={
		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N400","BSc Accounting","3 years","Full-time","Essex Business School","N400"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N404","BSc Accounting (Including Placement Year)","4 years","Full-time","Essex Business School","N404"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N402","BSc Accounting (Including Year Abroad)","4 years","Full-time","Essex Business School","N402"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N420","BSc Accounting and Finance","3 years","Full-time","Essex Business School","N420"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N422","BSc Accounting and Finance (Including Placement Year)","4 years","Full-time","Essex Business School","N422"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NNK3","BSc Accounting and Finance (Including Year Abroad)","4 years","Full-time","Essex Business School","NNK3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W411","BA Acting","3 years","Full-time","East 15 Acting School","W411"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N323","BSc Actuarial Science","3 years","Full-time","Mathematical Sciences","N323"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N233","BSc Actuarial Science (Including Placement Year)","4 years","Full-time","Mathematical Sciences","N233"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N324","BSc Actuarial Science (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","N324"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T700","BA American (United States) Studies","3 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T700"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T708","BA American (United States) Studies (Including Year Abroad)","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T708"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V314","BA Art History","3 years","Full-time","Philosophy and Art History (School of)","V314"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V35A","BA Art History (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","V35A"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C700","BSc Biochemistry","3 years","Full-time","Biological Sciences (School of)","C700"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C703","BSc Biochemistry (Including Year Abroad)","4 years","Full-time","Biological Sciences (School of)","C703"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C100","BSc Biological Sciences","3 years","Full-time","Biological Sciences (School of)","C100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C102","BSc Biological Sciences (Including Placement Year)","4 years","Full-time","Biological Sciences (School of)","C102"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C101","BSc Biological Sciences (Including Year Abroad)","4 years","Full-time","Biological Sciences (School of)","C101"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B990","BSc Biomedical Science","3 years","Full-time","Biological Sciences (School of)","B990"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B995","BSc Biomedical Science (Including Year Abroad)","4 years","Full-time","Biological Sciences (School of)","B995"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BBA+N100","BBA Business Administration","3 years","Full-time","Essex Business School","N100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BBA+N103","BBA Business Administration (Including Placement Year)","4 years","Full-time","Essex Business School","N103"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BBA+N110","BBA Business Administration (Including Year Abroad)","4 years","Full-time","Essex Business School","N110"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N200","BSc Business Management","3 years","Full-time","Essex Business School","N200"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N204","BSc Business Management (Including Placement Year)","4 years","Full-time","Essex Business School","N204"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N202","BSc Business Management (Including Year Abroad)","4 years","Full-time","Essex Business School","N202"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G400","BSc Computer Science","3 years","Full-time","Computer Science and Electronic Engineering (School of)","G400"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I101","BSc Computer Science (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I101"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G401","BSc Computer Science (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","G401"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W800","BA Creative Writing","3 years","Full-time","Literature, Film, and Theatre Studies","W800"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W801","BA Creative Writing (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","W801"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++M900","BA Criminology","3 years","Full-time","Sociology","M900"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++M904","BA Criminology (Including Placement Year)","4 years","Full-time","Sociology","M904"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++M901","BA Criminology (Including Year Abroad)","4 years","Full-time","Sociology","M901"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V351","BA Curatorial Studies","3 years","Full-time","Philosophy and Art History (School of)","V351"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V352","BA Curatorial Studies (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","V352"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W401","BA Drama","3 years","Full-time","Literature, Film, and Theatre Studies","W401"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W402","BA Drama (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","W402"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L101","BSc Economics","3 years","Full-time","Economics","L101"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L100","BA Economics","3 years","Full-time","Economics","L100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++0F66","BA Economics (Including Placement Year)","4 years","Full-time","Economics","0F66"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+0E45","BSc Economics (Including Placement Year)","4 years","Full-time","Economics","0E45"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L106","BA Economics (Including Year Abroad)","4 years","Full-time","Economics","L106"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L107","BSc Economics (Including Year Abroad)","4 years","Full-time","Economics","L107"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGH610","BEng Electronic Engineering","3 years","Full-time","Computer Science and Electronic Engineering (School of)","H610"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MENGH613","MEng Electronic Engineering","4 years","Full-time","Computer Science and Electronic Engineering (School of)","H613"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGHP10","BEng Electronic Engineering (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","HP10"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGH611","BEng Electronic Engineering (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","H611"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MENGH614","MEng Electronic Engineering (Integrated Masters, Including Placement Year)","5 years","Full-time","Computer Science and Electronic Engineering (School of)","H614"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q301","BA English Language","3 years","Full-time","Language and Linguistics","Q301"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q302","BA English Language (Including Year Abroad)","4 years","Full-time","Language and Linguistics","Q302"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QX31","BA English Language and Teaching English as a Foreign Language","3 years","Full-time","Language and Linguistics","QX31"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QXH1","BA English Language and Teaching English as a Foreign Language (Including Year Abroad)","4 years","Full-time","Language and Linguistics","QXH1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q300","BA English Literature","3 years","Full-time","Literature, Film, and Theatre Studies","Q300"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q321","BA English Literature (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","Q321"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R000","BA European Studies","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R000"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W620","BA Film Studies","3 years","Full-time","Literature, Film, and Theatre Studies","W620"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++P303","BA Film Studies (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","P303"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N300","BSc Finance","3 years","Full-time","Essex Business School","N300"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N304","BSc Finance (Including Placement Year)","4 years","Full-time","Essex Business School","N304"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N302","BSc Finance (Including Year Abroad)","4 years","Full-time","Essex Business School","N302"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L148","BA Financial Economics and Accounting","3 years","Full-time","Economics","L148"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L149","BA Financial Economics and Accounting (Including Year Abroad)","4 years","Full-time","Economics","L149"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=FDSCL510","FdSc Health Science (Care of the Adult)","2 years","Full-time","Health and Human Sciences","L510"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V100","BA History","3 years","Full-time","History","V100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++N863SS","BA Hotel Management","2 years","Full-time","Edge Hotel School","N863"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=FDA+N86GSS","FdA Hotel Management","1 year","Full-time","Edge Hotel School","N86G"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L258","BA International Relations","3 years","Full-time","Government","L258"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L260","BA International Relations (Including Placement Year)","4 years","Full-time","Government","L260"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L259","BA International Relations (Including Year Abroad)","4 years","Full-time","Government","L259"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T711","BA Latin American Studies (Including Year Abroad)","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T711"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+M100","LLB Law","3 years","Full-time","Law (School of)","M100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+M107","LLB Law (Including Placement Year)","4 years","Full-time","Law (School of)","M107"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V900","BA Liberal Arts","3 years","Full-time","Centre for Interdisciplinary Studies in Humanities","V900"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV00","BA Liberal Arts (Including Year Abroad)","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","QV00"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q100","BA Linguistics","3 years","Full-time","Language and Linguistics","Q100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q101","BA Linguistics (Including Year Abroad)","4 years","Full-time","Language and Linguistics","Q101"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N501","BSc Marketing","3 years","Full-time","Essex Business School","N501"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N504","BSc Marketing (Including Placement Year)","4 years","Full-time","Essex Business School","N504"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N502","BSc Marketing (Including Year Abroad)","4 years","Full-time","Essex Business School","N502"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G100","BSc Mathematics","3 years","Full-time","Mathematical Sciences","G100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G103","BSc Mathematics (Including Placement Year)","4 years","Full-time","Mathematical Sciences","G103"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G102","BSc Mathematics (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","G102"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+9K12","BSc Mathematics and Statistics","3 years","Full-time","Mathematical Sciences","9K12"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+9K13","BSc Mathematics and Statistics (Including Placement Year)","4 years","Full-time","Mathematical Sciences","9K13"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+5B43","BSc Mathematics and Statistics (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","5B43"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V140","BA Modern History","3 years","Full-time","History","V140"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VL12","BA Modern History and International Relations","3 years","Full-time","History","VL12"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV21","BA Modern History and Politics","3 years","Full-time","History","LV21"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R900","BA Modern Languages","4 years","Full-time","Language and Linguistics","R900"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RX91","BA Modern Languages and Teaching English as a Foreign Language","4 years","Full-time","Language and Linguistics","RX91"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++P500","BA Multimedia Journalism","3 years","Full-time","Literature, Film, and Theatre Studies","P500"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B740","BSc Nursing (Adult)","3 years","Full-time","Health and Human Sciences","B740"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B760","BSc Nursing (Mental Health)","3 years","Full-time","Health and Human Sciences","B760"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B93048","BSc Occupational Therapy","4 years","Part-time","Health and Human Sciences","B930"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=FDSCB750","FdSc Oral Health Science","2 years","Full-time","Health and Human Sciences","B750"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V500","BA Philosophy","3 years","Full-time","Philosophy and Art History (School of)","V500"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V501","BA Philosophy (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","V501"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B16048","BSc Physiotherapy","4 years","Part-time","Health and Human Sciences","B160"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L200","BA Politics","3 years","Full-time","Government","L200"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L203","BA Politics (Including Placement Year)","4 years","Full-time","Government","L203"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L201","BA Politics (Including Year Abroad)","4 years","Full-time","Government","L201"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++C890","BA Psychoanalytic Studieshttp://www.sx.ac.uk/coursefinder/a> (Subject to Approval)","3 years","Full-time","Psychoanalytic Studies","C890"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++C802","BA Psychology","3 years","Full-time","Psychology","C802"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C800","BSc Psychology","3 years","Full-time","Psychology","C800"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C803","BSc Psychology (Including Year Abroad)","4 years","Full-time","Psychology","C803"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++C801","BA Psychology (Including Year Abroad)","4 years","Full-time","Psychology","C801"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L500","BA Social Work","3 years","Full-time","Health and Human Sciences","L500"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L300","BA Sociology","3 years","Full-time","Sociology","L300"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L306","BA Sociology (Including Placement Year)","4 years","Full-time","Sociology","L306"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L301","BA Sociology (Including Year Abroad)","4 years","Full-time","Sociology","L301"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C600","BSc Sports and Exercise Science","3 years","Full-time","Biological Sciences (School of)","C600"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C606","BSc Sports and Exercise Science (Including Placement Year)","4 years","Full-time","Biological Sciences (School of)","C606"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C602","BSc Sports and Exercise Science (Including Year Abroad)","4 years","Full-time","Biological Sciences (School of)","C602"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C603","BSc Sports Therapy","3 years","Full-time","Health and Human Sciences","C603"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C604","BSc Sports Therapy (Including Placement Year)","4 years","Full-time","Health and Human Sciences","C604"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C605","BSc Sports Therapy (Including Year Abroad)","4 years","Full-time","Health and Human Sciences","C605"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MENGH642","MEng Telecommunication Engineering","4 years","Full-time","Computer Science and Electronic Engineering (School of)","H642"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++C847","BA Therapeutic Care","3 years","Full-time","Psychoanalytic Studies","C847"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NN24","BSc Accounting and Management","3 years","Full-time","Essex Business School","NN24"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NN27","BSc Accounting and Management (Including Placement Year)","4 years","Full-time","Essex Business School","NN27"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NNK2","BSc Accounting and Management (Including Year Abroad)","4 years","Full-time","Essex Business School","NNK2"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W83A","BA Acting (International)","3 years","Full-time","East 15 Acting School","W83A"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W496","BA Acting and Community Theatre","3 years","Full-time","East 15 Acting School","W496"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W441","BA Acting and Contemporary Theatre","3 years","Full-time","East 15 Acting School","W441"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W412","BA Acting and Stage Combat","3 years","Full-time","East 15 Acting School","W412"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VT27","BA American History","3 years","Full-time","History","VT27"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N390","BSc Banking and Finance","3 years","Full-time","Essex Business School","N390"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N392","BSc Banking and Finance (Including Placement Year)","4 years","Full-time","Essex Business School","N392"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NH90","BSc Banking and Finance (Including Year Abroad)","4 years","Full-time","Essex Business School","NH90"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C701","BSc Biochemistry (Including Placement Year)","4 years","Full-time","Biological Sciences (School of)","C701"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G610","BSc Computer Games","3 years","Full-time","Computer Science and Electronic Engineering (School of)","G610"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I610","BSc Computer Games (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I610"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G612","BSc Computer Games (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","G612"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGG420","BEng Computer Networks","3 years","Full-time","Computer Science and Electronic Engineering (School of)","G420"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGI120","BEng Computer Networks (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I120"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGG421","BEng Computer Networks (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","G421"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSCIG402","MSci Computer Science","4 years","Full-time","Computer Science and Electronic Engineering (School of)","G402"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MSCII100","MSci Computer Science (Integrated Masters, Including Placement Year)","5 years","Full-time","Computer Science and Electronic Engineering (School of)","I100"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGH650","BEng Computer Systems Engineering","3 years","Full-time","Computer Science and Electronic Engineering (School of)","H650"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGHP50","BEng Computer Systems Engineering (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","HP50"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGH651","BEng Computer Systems Engineering (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","H651"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGGH4P","BEng Computers with Electronics","3 years","Full-time","Computer Science and Electronic Engineering (School of)","GH4P"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGGH46","BEng Computers with Electronics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","GH46"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGI1H6","BEng Computers with Electronics (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I1H6"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGGH4Q","BEng Computers with Electronics (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","GH4Q"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MP93","BA Criminology and the Media","3 years","Full-time","Sociology","MP93"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MP94","BA Criminology and the Media (Including Placement Year)","4 years","Full-time","Sociology","MP94"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++M902","BA Criminology and the Media (Including Year Abroad)","4 years","Full-time","Sociology","M902"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I1G3","BSc Data Science and Analytics","3 years","Full-time","Computer Science and Electronic Engineering (School of)","I1G3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I1GF","BSc Data Science and Analytics (Including Foundation Year)","4 years","Full-time","International Academy","I1GF"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I1GB","BSc Data Science and Analytics (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I1GB"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I1GC","BSc Data Science and Analytics (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I1GC"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L170","BA Economic Analysis for Public Policy","3 years","Full-time","Economics","L170"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L172","BA Economic Analysis for Public Policy (Including Placement Year)","4 years","Full-time","Economics","L172"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L171","BA Economic Analysis for Public Policy (Including Year Abroad)","4 years","Full-time","Economics","L171"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L290","BA Elections, Public Opinions and Parties","3 years","Full-time","Government","L290"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L293","BA Elections, Public Opinions and Parties (Including Placement Year)","4 years","Full-time","Government","L293"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L291","BA Elections, Public Opinions and Parties (Including Year Abroad)","4 years","Full-time","Government","L291"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+M122","LLB English and French Law (Maitrise)","4 years","Full-time","Law (School of)","M122"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T720","BA English and United States Literature","3 years","Full-time","Literature, Film, and Theatre Studies","T720"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QT37","BA English and United States Literature (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","QT37"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QQ13","BA English Language and Linguistics","3 years","Full-time","Language and Linguistics","QQ13"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QQ3D","BA English Language and Linguistics (Including Year Abroad)","4 years","Full-time","Language and Linguistics","QQ3D"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QQ23","BA English Language and Literature","3 years","Full-time","Language and Linguistics","QQ23"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QQ32","BA English Language and Literature (Including Year Abroad)","4 years","Full-time","Language and Linguistics","QQ32"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QQ3C","BA English Language, Language Acquisition and Language Disorders","3 years","Full-time","Language and Linguistics","QQ3C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QB36","BA English Language, Language Acquisition and Language Disorders (Including Year Abroad)","4 years","Full-time","Language and Linguistics","QB36"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++PW38","BA Film and Creative Writing","3 years","Full-time","Literature, Film, and Theatre Studies","PW38"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++PWH8","BA Film and Creative Writing (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","PWH8"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QW26","BA Film Studies and Literature","3 years","Full-time","Literature, Film, and Theatre Studies","QW26"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++PQ32","BA Film Studies and Literature (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","PQ32"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N3T1","BSc Finance with Mandarin","4 years","Full-time","Essex Business School","N3T1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L111","BA Financial Economics","3 years","Full-time","Economics","L111"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L114","BSc Financial Economics","3 years","Full-time","Economics","L114"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++5A84","BA Financial Economics (Including Placement Year)","4 years","Full-time","Economics","5A84"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+0Q64","BSc Financial Economics (Including Placement Year)","4 years","Full-time","Economics","0Q64"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L195","BA Financial Economics (Including Year Abroad)","4 years","Full-time","Economics","L195"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L194","BSc Financial Economics (Including Year Abroad)","4 years","Full-time","Economics","L194"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N340","BSc Financial Management","3 years","Full-time","Essex Business School","N340"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N343","BSc Financial Management (Including Placement Year)","4 years","Full-time","Essex Business School","N343"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NH40","BSc Financial Management (Including Year Abroad)","4 years","Full-time","Essex Business School","NH40"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RR19","BA French Studies and Modern Languages","4 years","Full-time","Language and Linguistics","RR19"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C400","BSc Genetics","3 years","Full-time","Biological Sciences (School of)","C400"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C403","BSc Genetics (Including Placement Year)","4 years","Full-time","Biological Sciences (School of)","C403"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C402","BSc Genetics (Including Year Abroad)","4 years","Full-time","Biological Sciences (School of)","C402"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RR9F","BA German Studies and Modern Languages","4 years","Full-time","Language and Linguistics","RR9F"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V145","BA Global History","3 years","Full-time","History","V145"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MV91","BA History and Criminology","3 years","Full-time","History","MV91"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV21","BA History and Literature","3 years","Full-time","History","QV21"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV31","BA History and Sociology","3 years","Full-time","History","LV31"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+GH56","BSc Information and Communication Technology","3 years","Full-time","Computer Science and Electronic Engineering (School of)","GH56"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+I102","BSc Information and Communication Technology (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","I102"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+GH5P","BSc Information and Communication Technology (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","GH5P"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N120","BSc International Business and Entrepreneurship","3 years","Full-time","Essex Business School","N120"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N123","BSc International Business and Entrepreneurship (Including Placement Year)","4 years","Full-time","Essex Business School","N123"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N121","BSc International Business and Entrepreneurship (Including Year Abroad)","4 years","Full-time","Essex Business School","N121"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L116","BSc International Economics","3 years","Full-time","Economics","L116"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L115","BA International Economics","3 years","Full-time","Economics","L115"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+5H18","BSc International Economics (Including Placement Year)","4 years","Full-time","Economics","5H18"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++9O47","BA International Economics (Including Placement Year)","4 years","Full-time","Economics","9O47"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L163","BA International Economics (Including Year Abroad)","4 years","Full-time","Economics","L163"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L162","BSc International Economics (Including Year Abroad)","4 years","Full-time","Economics","L162"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=NONUY6OSBE","Occasional International Foundation Programme","1 year","Full-time","International Academy","Y6OS"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RR38","BA Italian Studies and Modern Languages","4 years","Full-time","Language and Linguistics","RR38"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q140","BA Language Studies","3 years","Full-time","Language and Linguistics","Q140"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T7N2","BA Latin American Studies with Business Management","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T7N2"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T7M9","BA Latin American Studies with Human Rights","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T7M9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+M120","LLB Law (Including Year Abroad)","4 years","Full-time","Law (School of)","M120"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+M103","LLB Law (Senior Status)","2 years","Full-time","Law (School of)","M103"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+MM19","LLB Law and Human Rights","4 years","Full-time","Law (School of)","MM19"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NN25","BSc Management and Marketing","3 years","Full-time","Essex Business School","NN25"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NN2M","BSc Management and Marketing (Including Placement Year)","4 years","Full-time","Essex Business School","NN2M"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NNF5","BSc Management and Marketing (Including Year Abroad)","4 years","Full-time","Essex Business School","NNF5"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L108","BA Management Economics","3 years","Full-time","Economics","L108"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L109","BSc Management Economics","3 years","Full-time","Economics","L109"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++9L11","BA Management Economics (Including Placement Year)","4 years","Full-time","Economics","9L11"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+5M00","BSc Management Economics (Including Placement Year)","4 years","Full-time","Economics","5M00"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L192","BA Management Economics (Including Year Abroad)","4 years","Full-time","Economics","L192"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L193","BSc Management Economics (Including Year Abroad)","4 years","Full-time","Economics","L193"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N1T1","BSc Management with Mandarin","4 years","Full-time","Essex Business School","N1T1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C164","BSc Marine Biology","3 years","Full-time","Biological Sciences (School of)","C164"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=MMB+C160","MMarBiol Marine Biology","4 years","Full-time","Biological Sciences (School of)","C160"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+CC60","BSc Marine Biology (Including Year Abroad)","4 years","Full-time","Biological Sciences (School of)","CC60"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G1F3","BSc Mathematics with Physics","3 years","Full-time","Mathematical Sciences","G1F3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+GCF3","BSc Mathematics with Physics (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","GCF3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LP33","BA Media, Culture and Society","3 years","Full-time","Sociology","LP33"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LP34","BA Media, Culture and Society (Including Placement Year)","4 years","Full-time","Sociology","LP34"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++PL33","BA Media, Culture and Society (Including Year Abroad)","4 years","Full-time","Sociology","PL33"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RQ93","BA Modern Languages and English Language","4 years","Full-time","Language and Linguistics","RQ93"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RQ91","BA Modern Languages and Linguistics","4 years","Full-time","Language and Linguistics","RQ91"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R800","BA Modern Languages with Film Production and Editing Skills","4 years","Full-time","Language and Linguistics","R800"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+B751","BSc Oral Health Science","1 year","Full-time","Health and Human Sciences","B751"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L0V0","BA Philosophy, Politics and Economics","3 years","Full-time","Government","L0V0"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L0V1","BA Philosophy, Politics and Economics (Including Placement Year)","4 years","Full-time","Government","L0V1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L0VA","BA Philosophy, Politics and Economics (Including Year Abroad)","4 years","Full-time","Government","L0VA"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV56","BA Philosophy, Religion and Ethics","3 years","Full-time","Philosophy and Art History (School of)","VV56"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV5P","BA Philosophy, Religion and Ethics (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VV5P"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W494","BA Physical Theatre","3 years","Full-time","East 15 Acting School","W494"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L150","BA Political Economics","3 years","Full-time","Government","L150"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L152","BA Political Economics (Including Placement Year)","4 years","Full-time","Government","L152"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L151","BA Political Economics (Including Year Abroad)","4 years","Full-time","Government","L151"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++7L29","BA Political Theory and Public Policy","3 years","Full-time","Government","7L29"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++7L30","BA Political Theory and Public Policy (Including Placement Year)","4 years","Full-time","Government","7L30"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++0A56","BA Political Theory and Public Policy (Including Year Abroad)","4 years","Full-time","Government","0A56"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RR58","BA Portuguese Studies and Modern Languages","4 years","Full-time","Language and Linguistics","RR58"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C808","BSc Psychology with Cognitive Neuroscience","3 years","Full-time","Psychology","C808"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+C806","BSc Psychology with Cognitive Neuroscience (Including Year Abroad)","4 years","Full-time","Psychology","C806"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V320","BA Social and Cultural History","3 years","Full-time","History","V320"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LM39","BA Sociology and Criminology","3 years","Full-time","Sociology","LM39"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LM38","BA Sociology and Criminology (Including Placement Year)","4 years","Full-time","Sociology","LM38"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LMH9","BA Sociology and Criminology (Including Year Abroad)","4 years","Full-time","Sociology","LMH9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++CL83","BA Sociology and Social Psychology","3 years","Full-time","Sociology","CL83"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++CLV3","BA Sociology and Social Psychology (Including Year Abroad)","4 years","Full-time","Sociology","CLV3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL36","BA Sociology with Social Anthropology","3 years","Full-time","Sociology","LL36"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL6P","BA Sociology with Social Anthropology (Including Placement Year)","4 years","Full-time","Sociology","LL6P"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL3P","BA Sociology with Social Anthropology (Including Year Abroad)","4 years","Full-time","Sociology","LL3P"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RR49","BA Spanish Studies and Modern Languages","4 years","Full-time","Language and Linguistics","RR49"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++RT97","BA Spanish, Portuguese and Brazilian Studies","4 years","Full-time","Language and Linguistics","RT97"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++DKAS","BA Stage and Production Management","3 years","Full-time","East 15 Acting School","DKAS"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGH641","BEng Telecommunication Engineering","3 years","Full-time","Computer Science and Electronic Engineering (School of)","H641"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGHPK1","BEng Telecommunication Engineering (Including Placement Year)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","HPK1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGHQ41","BEng Telecommunication Engineering (Including Year Abroad)","4 years","Full-time","Computer Science and Electronic Engineering (School of)","HQ41"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=CERXW410","Certificate of HE Theatre Arts","9 months","Full-time","East 15 Acting School","W410"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=CERXW410ST","Certificate of HE Theatre Arts","9 months","Full-time","East 15 Acting School","W410"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=FDA+LX51","FdA Therapeutic Communication and Therapeutic Organisations","2 years","Full-time","Psychoanalytic Studies","LX51"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=FDA+LX51ST","FdA Therapeutic Communication and Therapeutic Organisations","2 years","Full-time","Psychoanalytic Studies","LX51"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LX5C","BA Therapeutic Communication and Therapeutic Organisations","1 year","Full-time","Psychoanalytic Studies","LX5C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++W495","BA World Performance","3 years","Full-time","East 15 Acting School","W495"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NN42","BSc Accounting and Management (4 Years Including Foundation Year)","4 years","Full-time","International Academy","NN42"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NL41","BSc Accounting with Economics","3 years","Full-time","Essex Business School","NL41"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NL44","BSc Accounting with Economics (Including Placement Year)","4 years","Full-time","Essex Business School","NL44"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NKL1","BSc Accounting with Economics (Including Year Abroad)","4 years","Full-time","Essex Business School","NKL1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T7P3","BA American (United States) Studies with Film","3 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T7P3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++T7W6","BA American (United States) Studies with Film (Including Year Abroad)","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","T7W6"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV31","BA Art History and History","3 years","Full-time","Philosophy and Art History (School of)","VV31"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV3C","BA Art History and History (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VV3C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VR39","BA Art History and Modern Languages","4 years","Full-time","Philosophy and Art History (School of)","VR39"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V3R9","BA Art History with Modern Languages","4 years","Full-time","Philosophy and Art History (School of)","V3R9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NR3Y","BSc Banking and Finance with a Modern Language","4 years","Full-time","Essex Business School","NR3Y"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NRH9","BSc Banking, Finance and Modern Languages","4 years","Full-time","Essex Business School","NRH9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++NR19","BA Business Management and Modern Languages","4 years","Full-time","Essex Business School","NR19"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++N1R9","BA Business Management with a Modern Language","4 years","Full-time","Essex Business School","N1R9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MT2R","BA Criminology and American Studies","3 years","Full-time","Centre for Interdisciplinary Studies in Humanities","MT2R"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MT27","BA Criminology and American Studies (Including Year Abroad)","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","MT27"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L3C8","BA Criminology with Social Psychology","3 years","Full-time","Sociology","L3C8"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L3H8","BA Criminology with Social Psychology (Including Placement Year)","4 years","Full-time","Sociology","L3H8"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LHC8","BA Criminology with Social Psychology (Including Year Abroad)","4 years","Full-time","Sociology","LHC8"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QW24","BA Drama and Literature","3 years","Full-time","Literature, Film, and Theatre Studies","QW24"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++WQ42","BA Drama and Literature (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","WQ42"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+LG11","BSc Economics and Mathematics","3 years","Full-time","Mathematical Sciences","LG11"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+LG1C","BSc Economics and Mathematics (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","LG1C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL12","BA Economics and Politics","3 years","Full-time","Government","LL12"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL13","BA Economics and Politics (Including Placement Year)","4 years","Full-time","Government","LL13"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1R1","BA Economics with French","3 years","Full-time","Economics","L1R1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1RC","BA Economics with French (Including Year Abroad)","4 years","Full-time","Economics","L1RC"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1R2","BA Economics with German","3 years","Full-time","Economics","L1R2"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1RF","BA Economics with German (Including Year Abroad)","4 years","Full-time","Economics","L1RF"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1R3","BA Economics with Italian","3 years","Full-time","Economics","L1R3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1RH","BA Economics with Italian (Including Year Abroad)","4 years","Full-time","Economics","L1RH"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L1G1","BSc Economics with Mathematics","3 years","Full-time","Economics","L1G1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L1GC","BSc Economics with Mathematics (Including Year Abroad)","4 years","Full-time","Economics","L1GC"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1R5","BA Economics with Portuguese","3 years","Full-time","Economics","L1R5"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1RM","BA Economics with Portuguese (Including Year Abroad)","4 years","Full-time","Economics","L1RM"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1R4","BA Economics with Spanish","3 years","Full-time","Economics","L1R4"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L1RK","BA Economics with Spanish (Including Year Abroad)","4 years","Full-time","Economics","L1RK"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV31","BA English Language and History","3 years","Full-time","Language and Linguistics","QV31"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV3C","BA English Language and History (Including Year Abroad)","4 years","Full-time","Language and Linguistics","QV3C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R9T9","BA European Studies and Modern Languages","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R9T9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R9R1","BA European Studies with French","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R9R1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R9R2","BA European Studies with German","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R9R2"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R9R3","BA European Studies with Italian","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R9R3"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R9L2","BA European Studies with Politics","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R9L2"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R9R4","BA European Studies with Spanish","4 years","Full-time","Centre for Interdisciplinary Studies in Humanities","R9R4"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VW36","BA Film Studies and Art History","3 years","Full-time","Philosophy and Art History (School of)","VW36"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++PV33","BA Film Studies and Art History (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","PV33"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+GN13","BSc Finance and Mathematics","3 years","Full-time","Mathematical Sciences","GN13"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+GN1H","BSc Finance and Mathematics (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","GN1H"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NR39","BSc Finance and Modern Languages","4 years","Full-time","Essex Business School","NR39"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N3R9","BSc Finance with a Modern Language","4 years","Full-time","Essex Business School","N3R9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV11","BA History and Economics","3 years","Full-time","Economics","LV11"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VL11","BA History and Economics (Including Year Abroad)","4 years","Full-time","Economics","VL11"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VW16","BA History and Film Studies","3 years","Full-time","History","VW16"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V1W6","BA History with Film Studies","3 years","Full-time","History","V1W6"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V1L2","BA History with Human Rights","3 years","Full-time","History","V1L2"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V1R9","BA History with Modern Languages","4 years","Full-time","History","V1R9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LRF9","BA International Relations and Modern Languages","4 years","Full-time","Language and Linguistics","LRF9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+MV15","LLB Law and Philosophy","4 years","Full-time","Law (School of)","MV15"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+ML12","LLB Law and Politics","4 years","Full-time","Law (School of)","ML12"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LQ31","BA Linguistics and Sociology","3 years","Full-time","Language and Linguistics","LQ31"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L390","BA Linguistics and Sociology (Including Year Abroad)","4 years","Full-time","Language and Linguistics","L390"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV23","BA Literature and Art History","3 years","Full-time","Philosophy and Art History (School of)","QV23"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV32","BA Literature and Art History (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","QV32"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QRF9","BA Literature and Modern Languages","4 years","Full-time","Language and Linguistics","QRF9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LQ32","BA Literature and Sociology","3 years","Full-time","Literature, Film, and Theatre Studies","LQ32"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QL23","BA Literature and Sociology (Including Year Abroad)","4 years","Full-time","Literature, Film, and Theatre Studies","QL23"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q2R9","BA Literature with Modern Languages","4 years","Full-time","Language and Linguistics","Q2R9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G1GK","BSc Mathematics with Computing","3 years","Full-time","Mathematical Sciences","G1GK"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G1IK","BSc Mathematics with Computing (Including Placement Year)","4 years","Full-time","Mathematical Sciences","G1IK"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G1G4","BSc Mathematics with Computing (Including Year Abroad)","4 years","Full-time","Mathematical Sciences","G1G4"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R8T7","BA Modern Languages with Latin American Studies","4 years","Full-time","Language and Linguistics","R8T7"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV53","BA Philosophy and Art History","3 years","Full-time","Philosophy and Art History (School of)","VV53"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV5H","BA Philosophy and Art History (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VV5H"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV15","BA Philosophy and History","3 years","Full-time","Philosophy and Art History (School of)","VV15"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV5C","BA Philosophy and History (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VV5C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MVC5","BA Philosophy and Law","3 years","Full-time","Philosophy and Art History (School of)","MVC5"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VM51","BA Philosophy and Law (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VM51"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV25","BA Philosophy and Literature","3 years","Full-time","Philosophy and Art History (School of)","QV25"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VQ5F","BA Philosophy and Literature (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VQ5F"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VR59","BA Philosophy and Modern Languages","4 years","Full-time","Language and Linguistics","VR59"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV25","BA Philosophy and Politics","3 years","Full-time","Philosophy and Art History (School of)","LV25"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV2M","BA Philosophy and Politics (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","LV2M"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV35","BA Philosophy and Sociology","3 years","Full-time","Philosophy and Art History (School of)","LV35"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VL53","BA Philosophy and Sociology (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","VL53"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V5M9","BA Philosophy with Human Rights","3 years","Full-time","Philosophy and Art History (School of)","V5M9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V5MX","BA Philosophy with Human Rights (Including Year Abroad)","4 years","Full-time","Philosophy and Art History (School of)","V5MX"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V5R9","BA Philosophy with Modern Languages","4 years","Full-time","Language and Linguistics","V5R9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LR29","BA Politics and Modern Languages","4 years","Full-time","Language and Linguistics","LR29"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L2M9","BA Politics with Human Rights","3 years","Full-time","Government","L2M9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L219","BA Politics with Human Rights (Including Placement Year)","4 years","Full-time","Government","L219"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LFM9","BA Politics with Human Rights (Including Year Abroad)","4 years","Full-time","Government","LFM9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL32","BA Sociology and Politics","3 years","Full-time","Sociology","LL32"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL24","BA Sociology and Politics (Including Placement Year)","4 years","Full-time","Sociology","LL24"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL23","BA Sociology and Politics (Including Year Abroad)","4 years","Full-time","Sociology","LL23"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L3M9","BA Sociology with Human Rights","3 years","Full-time","Sociology","L3M9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L3J9","BA Sociology with Human Rights (Including Placement Year)","4 years","Full-time","Sociology","L3J9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LMJ9","BA Sociology with Human Rights (Including Year Abroad)","4 years","Full-time","Sociology","LMJ9"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LJC8","BA Sociology with Psychosocial Studies","3 years","Full-time","Sociology","LJC8"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LCJ8","BA Sociology with Psychosocial Studies (Including Placement Year)","4 years","Full-time","Sociology","LCJ8"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LJ8C","BA Sociology with Psychosocial Studies (Including Year Abroad)","4 years","Full-time","Sociology","LJ8C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++R4T7","BA Spanish Studies with Latin American Studies","4 years","Full-time","Language and Linguistics","R4T7"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N401","BSc Accounting (4 Years Including Foundation Year)","4 years","Full-time","International Academy","N401"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+NN43","BSc Accounting and Finance (4 Years Including Foundation Year)","4 years","Full-time","International Academy","NN43"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N4L1","BSc Accounting with Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","N4L1"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VT2T","BA American History (4 Years Including Foundation Year)","4 years","Full-time","International Academy","VT2T"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VT2R","BA American History (Including Year Abroad)","4 years","Full-time","History","VT2R"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V350","BA Art History (4 Years Including Foundation Year)","4 years","Full-time","International Academy","V350"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=DINTNN14","Intl Diploma Business (Accounting, Finance and Management)","1 year","Full-time","International Academy","NN14"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N201","BSc Business Management (4 Years Including Foundation Year)","4 years","Full-time","International Academy","N201"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+G403","BSc Computer Science (4 Years Including Foundation Year)","4 years","Full-time","International Academy","G403"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++M903","BA Criminology (4 Years Including Foundation Year)","4 years","Full-time","International Academy","M903"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L103","BSc Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L103"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L102","BA Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L102"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LL1F","BA Economics and Politics (Including Year Abroad)","4 years","Full-time","Government","LL1F"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGH61P","BEng Electronic Engineering (4 Years Including Foundation Year)","4 years","Full-time","International Academy","H61P"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++Q320","BA English Literature (4 Years Including Foundation Year)","4 years","Full-time","International Academy","Q320"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N301","BSc Finance (4 Years Including Foundation Year)","4 years","Full-time","International Academy","N301"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L118","BA Financial Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L118"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L117","BSc Financial Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L117"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+N341","BSc Financial Management (4 Years Including Foundation Year)","4 years","Full-time","International Academy","N341"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V146","BA Global History (Including Year Abroad)","4 years","Full-time","History","V146"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V102","BA History (4 Years Including Foundation Year)","4 years","Full-time","International Academy","V102"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V101","BA History (Including Year Abroad)","4 years","Full-time","History","V101"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++MV9C","BA History and Criminology (Including Year Abroad)","4 years","Full-time","History","MV9C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V1WQ","BA History and Film Studies (Including Year Abroad)","4 years","Full-time","History","V1WQ"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV2C","BA History and Literature (4 Years Including Foundation Year)","4 years","Full-time","International Academy","QV2C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VQ12","BA History and Literature (Including Year Abroad)","4 years","Full-time","History","VQ12"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV3C","BA History and Sociology (Including Year Abroad)","4 years","Full-time","History","LV3C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V1WP","BA History with Film Studies (Including Year Abroad)","4 years","Full-time","History","V1WP"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V1LF","BA History with Human Rights (Including Year Abroad)","4 years","Full-time","History","V1LF"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L161","BSc International Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L161"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L160","BA International Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L160"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L250","BA International Relations (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L250"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=LLB+M101","LLB Law (4 Years Including Foundation Year)","4 years","Full-time","International Academy","M101"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LQV0","BA Liberal Arts (4 Years Including Foundation Year)","4 years","Full-time","International Academy","LQV0"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++QV2H","BA Literature and Art History (4 Years Including Foundation Year)","4 years","Full-time","International Academy","QV2H"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L190","BA Management Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L190"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BSC+L191","BSc Management Economics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L191"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++P300","BA Media, Culture and Society (4 Years Including Foundation Year)","4 years","Full-time","International Academy","P300"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V144","BA Modern History (4 Years Including Foundation Year)","4 years","Full-time","International Academy","V144"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V149","BA Modern History (Including Year Abroad)","4 years","Full-time","History","V149"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VL1F","BA Modern History and International Relations (Including Year Abroad)","4 years","Full-time","History","VL1F"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LV2C","BA Modern History and Politics (Including Year Abroad)","4 years","Full-time","History","LV2C"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V502","BA Philosophy (4 Years Including Foundation Year)","4 years","Full-time","International Academy","V502"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VV51","BA Philosophy and History (4 Years Including Foundation Year)","4 years","Full-time","International Academy","VV51"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++VQ52","BA Philosophy and Literature (4 Years Including Foundation Year)","4 years","Full-time","International Academy","VQ52"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L202","BA Politics (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L202"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++V324","BA Social and Cultural History (Including Year Abroad)","4 years","Full-time","History","V324"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++L304","BA Sociology (4 Years Including Foundation Year)","4 years","Full-time","International Academy","L304"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BA++LMHX","BA Sociology and Criminology (4 Years Including Foundation Year)","4 years","Full-time","International Academy","LMHX"},

		{"http://www.sx.ac.uk/coursefinder/course_details.aspx?course=BENGHP41","BEng Telecommunication Engineering (4 Years Including Foundation Year)","4 years","Full-time","International Academy","HP41"}};
}
