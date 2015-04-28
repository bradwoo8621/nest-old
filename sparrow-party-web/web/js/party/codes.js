var CodesRenderer = {
	regionRenderer : function(code) {
		return code.id + "-" + code.name;
	},
};
var Codes = {
	// party type
	partyTypeList : new Codes([ {
		id : "I",
		text : "Individual"
	}, {
		id : "O",
		text : "Organization"
	} ]),
	// party role type
	// type: O: Organization; I: Individual; B: Both
	roleTypeList : new Codes([ {
		id : "EDU",
		text : "Education Organization",
		type : "O"
	}, {
		id : "AGI",
		text : "Individual Agent",
		type : "I"
	}, {
		id : "AGO",
		text : "Organization Agent",
		type : "O"
	}, {
		id : "MBR",
		text : "My Branch",
		type : "O"
	}, {
		id : "MDP",
		text : "My Department",
		type : "O"
	}, {
		id : "MEM",
		text : "My Employee",
		type : "I"
	}, {
		id : "RDP",
		text : "Related Department",
		type : "O"
	}, {
		id : "REP",
		text : "Related Employee",
		type : "I"
	} ]),
	// country list
	countryList : new Codes([ {
		id : "AFG",
		name : "AFGHANISTAN"
	}, {
		id : "ALB",
		name : "ALBANIA"
	}, {
		id : "DZA",
		name : "ALGERIA"
	}, {
		id : "ASM",
		name : "AMERICAN SAMOA"
	}, {
		id : "AND",
		name : "ANDORRA"
	}, {
		id : "AGO",
		name : "ANGOLA"
	}, {
		id : "AIA",
		name : "ANGUILLA"
	}, {
		id : "ATA",
		name : "ANTARCTICA"
	}, {
		id : "ATG",
		name : "ANTIGUA AND BARBUDA"
	}, {
		id : "ARG",
		name : "ARGENTINA"
	}, {
		id : "ARM",
		name : "ARMENIA"
	}, {
		id : "ABW",
		name : "ARUBA"
	}, {
		id : "AUS",
		name : "AUSTRALIA"
	}, {
		id : "AUT",
		name : "AUSTRIA"
	}, {
		id : "AZE",
		name : "AZERBAIJAN"
	}, {
		id : "BHS",
		name : "BAHAMAS"
	}, {
		id : "BHR",
		name : "BAHRAIN"
	}, {
		id : "BGD",
		name : "BANGLADESH"
	}, {
		id : "BRB",
		name : "BARBADOS"
	}, {
		id : "BLR",
		name : "BELARUS"
	}, {
		id : "BEL",
		name : "BELGIUM"
	}, {
		id : "BLZ",
		name : "BELIZE"
	}, {
		id : "BEN",
		name : "BENIN"
	}, {
		id : "BMU",
		name : "BERMUDA"
	}, {
		id : "BTN",
		name : "BHUTAN"
	}, {
		id : "BOL",
		name : "BOLIVIA"
	}, {
		id : "BIH",
		name : "BOSNIA AND HERZEGOWINA"
	}, {
		id : "BWA",
		name : "BOTSWANA"
	}, {
		id : "BVT",
		name : "BOUVET ISLAND (Norway)"
	}, {
		id : "BRA",
		name : "BRAZIL"
	}, {
		id : "IOT",
		name : "BRITISH INDIAN OCEAN TERRITORY"
	}, {
		id : "BRN",
		name : "BRUNEI DARUSSALAM"
	}, {
		id : "BGR",
		name : "BULGARIA"
	}, {
		id : "BFA",
		name : "BURKINA FASO"
	}, {
		id : "BDI",
		name : "BURUNDI"
	}, {
		id : "KHM",
		name : "CAMBODIA"
	}, {
		id : "CMR",
		name : "CAMEROON"
	}, {
		id : "CAN",
		name : "CANADA"
	}, {
		id : "CPV",
		name : "CAPE VERDE"
	}, {
		id : "CYM",
		name : "CAYMAN ISLANDS"
	}, {
		id : "CAF",
		name : "CENTRAL AFRICAN REPUBLIC"
	}, {
		id : "TCD",
		name : "CHAD"
	}, {
		id : "CHL",
		name : "CHILE"
	}, {
		id : "CHN",
		name : "CHINA"
	}, {
		id : "CXR",
		name : "CHRISTMAS ISLAND"
	}, {
		id : "CCK",
		name : "COCOS (KEELING) ISLANDS (Austrailia)"
	}, {
		id : "COL",
		name : "COLOMBIA"
	}, {
		id : "COM",
		name : "COMOROS"
	}, {
		id : "COG",
		name : "CONGO"
	}, {
		id : "COD",
		name : "CONGO, THE DRC"
	}, {
		id : "COK",
		name : "COOK ISLANDS"
	}, {
		id : "CRI",
		name : "COSTA RICA"
	}, {
		id : "CIV",
		name : "COTE D'IVOIRE"
	}, {
		id : "HRV",
		name : "CROATIA (local name: Hrvatska)"
	}, {
		id : "CUB",
		name : "CUBA"
	}, {
		id : "CYP",
		name : "CYPRUS"
	}, {
		id : "CZE",
		name : "CZECH REPUBLIC"
	}, {
		id : "DNK",
		name : "DENMARK"
	}, {
		id : "DJI",
		name : "DJIBOUTI"
	}, {
		id : "DMA",
		name : "DOMINICA"
	}, {
		id : "DOM",
		name : "DOMINICAN REPUBLIC"
	}, {
		id : "TMP",
		name : "EAST TIMOR"
	}, {
		id : "ECU",
		name : "ECUADOR"
	}, {
		id : "EGY",
		name : "EGYPT"
	}, {
		id : "SLV",
		name : "EL SALVADOR"
	}, {
		id : "GNQ",
		name : "EQUATORIAL GUINEA"
	}, {
		id : "ERI",
		name : "ERITREA"
	}, {
		id : "EST",
		name : "ESTONIA"
	}, {
		id : "ETH",
		name : "ETHIOPIA"
	}, {
		id : "FLK",
		name : "FALKLAND ISLANDS (MALVINAS)"
	}, {
		id : "FRO",
		name : "FAROE ISLANDS"
	}, {
		id : "FJI",
		name : "FIJI"
	}, {
		id : "FIN",
		name : "FINLAND"
	}, {
		id : "FRA",
		name : "FRANCE"
	}, {
		id : "FXX",
		name : "FRANCE, METROPOLITAN"
	}, {
		id : "GUF",
		name : "FRENCH GUIANA"
	}, {
		id : "PYF",
		name : "FRENCH POLYNESIA"
	}, {
		id : "ATF",
		name : "FRENCH SOUTHERN TERRITORIES"
	}, {
		id : "GAB",
		name : "GABON"
	}, {
		id : "GMB",
		name : "GAMBIA"
	}, {
		id : "GEO",
		name : "GEORGIA"
	}, {
		id : "DEU",
		name : "GERMANY"
	}, {
		id : "GHA",
		name : "GHANA"
	}, {
		id : "GIB",
		name : "GIBRALTAR"
	}, {
		id : "GRC",
		name : "GREECE"
	}, {
		id : "GRL",
		name : "GREENLAND"
	}, {
		id : "GRD",
		name : "GRENADA"
	}, {
		id : "GLP",
		name : "GUADELOUPE"
	}, {
		id : "GUM",
		name : "GUAM"
	}, {
		id : "GTM",
		name : "GUATEMALA"
	}, {
		id : "GIN",
		name : "GUINEA"
	}, {
		id : "GNB",
		name : "GUINEA-BISSAU"
	}, {
		id : "GUY",
		name : "GUYANA"
	}, {
		id : "HTI",
		name : "HAITI"
	}, {
		id : "HMD",
		name : "HEARD AND MC DONALD ISLANDS"
	}, {
		id : "VAT",
		name : "HOLY SEE (VATICAN CITY STATE)"
	}, {
		id : "HND",
		name : "HONDURAS"
	}, {
		id : "HKG",
		name : "HONG KONG(CHINA)"
	}, {
		id : "HUN",
		name : "HUNGARY"
	}, {
		id : "ISL",
		name : "ICELAND"
	}, {
		id : "IND",
		name : "INDIA"
	}, {
		id : "IDN",
		name : "INDONESIA"
	}, {
		id : "IRN",
		name : "IRAN (ISLAMIC REPUBLIC OF)"
	}, {
		id : "IRQ",
		name : "IRAQ"
	}, {
		id : "IRL",
		name : "IRELAND"
	}, {
		id : "ISR",
		name : "ISRAEL"
	}, {
		id : "ITA",
		name : "ITALY"
	}, {
		id : "JAM",
		name : "JAMAICA"
	}, {
		id : "JPN",
		name : "JAPAN"
	}, {
		id : "JOR",
		name : "JORDAN"
	}, {
		id : "KAZ",
		name : "KAZAKHSTAN"
	}, {
		id : "KEN",
		name : "KENYA"
	}, {
		id : "KIR",
		name : "KIRIBATI"
	}, {
		id : "PRK",
		name : "KOREA, D.P.R.O."
	}, {
		id : "KOR",
		name : "KOREA, REPUBLIC OF"
	}, {
		id : "KWT",
		name : "KUWAIT"
	}, {
		id : "KGZ",
		name : "KYRGYZSTAN"
	}, {
		id : "LAO",
		name : "LAOS"
	}, {
		id : "LVA",
		name : "LATVIA"
	}, {
		id : "LBN",
		name : "LEBANON"
	}, {
		id : "LSO",
		name : "LESOTHO"
	}, {
		id : "LBR",
		name : "LIBERIA"
	}, {
		id : "LBY",
		name : "LIBYAN ARAB JAMAHIRIYA"
	}, {
		id : "LIE",
		name : "LIECHTENSTEIN"
	}, {
		id : "LTU",
		name : "LITHUANIA"
	}, {
		id : "LUX",
		name : "LUXEMBOURG"
	}, {
		id : "MAC",
		name : "MACAU(CHINA)"
	}, {
		id : "MKD",
		name : "MACEDONIA"
	}, {
		id : "MDG",
		name : "MADAGASCAR"
	}, {
		id : "MWI",
		name : "MALAWI"
	}, {
		id : "MYS",
		name : "MALAYSIA"
	}, {
		id : "MDV",
		name : "MALDIVES"
	}, {
		id : "MLI",
		name : "MALI"
	}, {
		id : "MLT",
		name : "MALTA"
	}, {
		id : "MHL",
		name : "MARSHALL ISLANDS"
	}, {
		id : "MTQ",
		name : "MARTINIQUE"
	}, {
		id : "MRT",
		name : "MAURITANIA"
	}, {
		id : "MUS",
		name : "MAURITIUS"
	}, {
		id : "MYT",
		name : "MAYOTTE"
	}, {
		id : "MEX",
		name : "MEXICO"
	}, {
		id : "FSM",
		name : "MICRONESIA, FEDERATED STATES OF"
	}, {
		id : "MDA",
		name : "MOLDOVA  REPUBLIC OF"
	}, {
		id : "MCO",
		name : "MONACO"
	}, {
		id : "MNG",
		name : "MONGOLIA"
	}, {
		id : "MNE",
		name : "MONTENEGRO"
	}, {
		id : "MSR",
		name : "MONTSERRAT"
	}, {
		id : "MAR",
		name : "MOROCCO"
	}, {
		id : "MOZ",
		name : "MOZAMBIQUE"
	}, {
		id : "MMR",
		name : "MYANMAR (Burma)"
	}, {
		id : "NAM",
		name : "NAMIBIA"
	}, {
		id : "NRU",
		name : "NAURU"
	}, {
		id : "NPL",
		name : "NEPAL"
	}, {
		id : "NLD",
		name : "NETHERLANDS"
	}, {
		id : "ANT",
		name : "NETHERLANDS ANTILLES"
	}, {
		id : "NCL",
		name : "NEW CALEDONIA"
	}, {
		id : "NZL",
		name : "NEW ZEALAND"
	}, {
		id : "NIC",
		name : "NICARAGUA"
	}, {
		id : "NER",
		name : "NIGER"
	}, {
		id : "NGA",
		name : "NIGERIA"
	}, {
		id : "NIU",
		name : "NIUE"
	}, {
		id : "NFK",
		name : "NORFOLK ISLAND"
	}, {
		id : "MNP",
		name : "NORTHERN MARIANA ISLANDS"
	}, {
		id : "NOR",
		name : "NORWAY"
	}, {
		id : "OMN",
		name : "OMAN"
	}, {
		id : "PAK",
		name : "PAKISTAN"
	}, {
		id : "PLW",
		name : "PALAU"
	}, {
		id : "PAN",
		name : "PANAMA"
	}, {
		id : "PNG",
		name : "PAPUA NEW GUINEA"
	}, {
		id : "PRY",
		name : "PARAGUAY"
	}, {
		id : "PER",
		name : "PERU"
	}, {
		id : "PHL",
		name : "PHILIPPINES"
	}, {
		id : "PCN",
		name : "PITCAIRN"
	}, {
		id : "POL",
		name : "POLAND"
	}, {
		id : "PRT",
		name : "PORTUGAL"
	}, {
		id : "PRI",
		name : "PUERTO RICO"
	}, {
		id : "QAT",
		name : "QATAR"
	}, {
		id : "REU",
		name : "REUNION"
	}, {
		id : "ROM",
		name : "ROMANIA"
	}, {
		id : "RUS",
		name : "RUSSIAN FEDERATION"
	}, {
		id : "RWA",
		name : "RWANDA"
	}, {
		id : "KNA",
		name : "SAINT KITTS AND NEVIS"
	}, {
		id : "LCA",
		name : "SAINT LUCIA"
	}, {
		id : "VCT",
		name : "SAINT VINCENT AND THE GRENADINES"
	}, {
		id : "WSM",
		name : "SAMOA"
	}, {
		id : "SMR",
		name : "SAN MARINO"
	}, {
		id : "STP",
		name : "SAO TOME AND PRINCIPE"
	}, {
		id : "SAU",
		name : "SAUDI ARABIA"
	}, {
		id : "SEN",
		name : "SENEGAL"
	}, {
		id : "SRB",
		name : "SERBIA"
	}, {
		id : "SYC",
		name : "SEYCHELLES"
	}, {
		id : "SLE",
		name : "SIERRA LEONE"
	}, {
		id : "SGP",
		name : "SINGAPORE"
	}, {
		id : "SVK",
		name : "SLOVAKIA (Slovak Republic)"
	}, {
		id : "SVN",
		name : "SLOVENIA"
	}, {
		id : "SLB",
		name : "SOLOMON ISLANDS"
	}, {
		id : "SOM",
		name : "SOMALIA"
	}, {
		id : "ZAF",
		name : "SOUTH AFRICA"
	}, {
		id : "SSD",
		name : "SOUTH SUDAN"
	}, {
		id : "SGS",
		name : "SOUTH GEORGIA AND SOUTH S.S."
	}, {
		id : "ESP",
		name : "SPAIN"
	}, {
		id : "LKA",
		name : "SRI LANKA"
	}, {
		id : "SHN",
		name : "ST. HELENA"
	}, {
		id : "SPM",
		name : "ST. PIERRE AND MIQUELON"
	}, {
		id : "SDN",
		name : "SUDAN"
	}, {
		id : "SUR",
		name : "SURINAME"
	}, {
		id : "SJM",
		name : "SVALBARDANDJAN MAYEN ISLANDS"
	}, {
		id : "SWZ",
		name : "SWAZILAND"
	}, {
		id : "SWE",
		name : "SWEDEN"
	}, {
		id : "CHE",
		name : "SWITZERLAND"
	}, {
		id : "SYR",
		name : "SYRIAN ARAB REPUBLIC"
	}, {
		id : "TWN",
		name : "TAIWAN, PROVINCE OF CHINA"
	}, {
		id : "TJK",
		name : "TAJIKISTAN"
	}, {
		id : "TZA",
		name : "TANZANIA, UNITED REPUBLIC OF"
	}, {
		id : "THA",
		name : "THAILAND"
	}, {
		id : "TGO",
		name : "TOGO"
	}, {
		id : "TKL",
		name : "TOKELAU"
	}, {
		id : "TON",
		name : "TONGA"
	}, {
		id : "TTO",
		name : "TRINIDAD AND TOBAGO"
	}, {
		id : "TUN",
		name : "TUNISIA"
	}, {
		id : "TUR",
		name : "TURKEY"
	}, {
		id : "TKM",
		name : "TURKMENISTAN"
	}, {
		id : "TCA",
		name : "TURKS AND CAICOS ISLANDS"
	}, {
		id : "TUV",
		name : "TUVALU"
	}, {
		id : "UGA",
		name : "UGANDA"
	}, {
		id : "UKR",
		name : "UKRAINE"
	}, {
		id : "ARE",
		name : "UNITED ARAB EMIRATES"
	}, {
		id : "GBR",
		name : "UNITED KINGDOM"
	}, {
		id : "USA",
		name : "UNITED STATES"
	}, {
		id : "UMI",
		name : "U.S. MINOR ISLANDS"
	}, {
		id : "URY",
		name : "URUGUAY"
	}, {
		id : "UZB",
		name : "UZBEKISTAN"
	}, {
		id : "VUT",
		name : "VANUATU"
	}, {
		id : "VEN",
		name : "VENEZUELA"
	}, {
		id : "VNM",
		name : "VIETNAM"
	}, {
		id : "VGB",
		name : "VIRGIN ISLANDS (BRITISH)"
	}, {
		id : "VIR",
		name : "VIRGIN ISLANDS (U.S.)"
	}, {
		id : "WLF",
		name : "WALLIS AND FUTUNA ISLANDS"
	}, {
		id : "ESH",
		name : "WESTERN SAHARA"
	}, {
		id : "YEM",
		name : "YEMEN"
	}, {
		id : "ZMB",
		name : "ZAMBIA"
	}, {
		id : "ZWE",
		name : "ZIMBABWE"
	} ], CodesRenderer.regionRenderer),
	// province list
	provinceList : new Codes([ {
		id : "AH",
		name : "安徽省",
		type : "CHN"
	}, {
		id : "BJ",
		name : "北京市",
		type : "CHN"
	}, {
		id : "CQ",
		name : "重庆市",
		type : "CHN"
	}, {
		id : "FJ",
		name : "福建省",
		type : "CHN"
	}, {
		id : "GD",
		name : "广东省",
		type : "CHN"
	}, {
		id : "GS",
		name : "甘肃省",
		type : "CHN"
	}, {
		id : "GX",
		name : "广西壮族自治区",
		type : "CHN"
	}, {
		id : "GZ",
		name : "贵州省",
		type : "CHN"
	}, {
		id : "HA",
		name : "河南省",
		type : "CHN"
	}, {
		id : "HB",
		name : "湖北省",
		type : "CHN"
	}, {
		id : "HE",
		name : "河北省",
		type : "CHN"
	}, {
		id : "HI",
		name : "海南省",
		type : "CHN"
	}, {
		id : "HK",
		name : "香港特别行政区",
		type : "CHN"
	}, {
		id : "HL",
		name : "黑龙江省",
		type : "CHN"
	}, {
		id : "HN",
		name : "湖南省",
		type : "CHN"
	}, {
		id : "JL",
		name : "吉林省",
		type : "CHN"
	}, {
		id : "JS",
		name : "江苏省",
		type : "CHN"
	}, {
		id : "JX",
		name : "江西省",
		type : "CHN"
	}, {
		id : "LN",
		name : "辽宁省",
		type : "CHN"
	}, {
		id : "MO",
		name : "澳门特别行政区",
		type : "CHN"
	}, {
		id : "NM",
		name : "内蒙古自治区",
		type : "CHN"
	}, {
		id : "NX",
		name : "宁夏回族自治区",
		type : "CHN"
	}, {
		id : "QH",
		name : "青海省",
		type : "CHN"
	}, {
		id : "SC",
		name : "四川省",
		type : "CHN"
	}, {
		id : "SD",
		name : "山东省",
		type : "CHN"
	}, {
		id : "SH",
		name : "上海市",
		type : "CHN"
	}, {
		id : "SN",
		name : "陕西省",
		type : "CHN"
	}, {
		id : "SX",
		name : "山西省",
		type : "CHN"
	}, {
		id : "TJ",
		name : "天津市",
		type : "CHN"
	}, {
		id : "TW",
		name : "台湾省",
		type : "CHN"
	}, {
		id : "XJ",
		name : "新疆维吾尔自治区",
		type : "CHN"
	}, {
		id : "XZ",
		name : "西藏自治区",
		type : "CHN"
	}, {
		id : "YN",
		name : "云南省",
		type : "CHN"
	}, {
		id : "ZJ",
		name : "浙江省",
		type : "CHN"
	} ], CodesRenderer.regionRenderer),
	cityList : new Codes([ {
		id : "SH",
		name : "上海市",
		type : "SH"
	} ], CodesRenderer.regionRenderer),
	districtList : new Codes([ {
		id : "BS",
		name : "宝山区",
		type : "SH"
	}, {
		id : "CM",
		name : "崇明县",
		type : "SH"
	}, {
		id : "CN",
		name : "长宁区",
		type : "SH"
	}, {
		id : "FX",
		name : "奉贤区",
		type : "SH"
	}, {
		id : "HK",
		name : "虹口区",
		type : "SH"
	}, {
		id : "HP",
		name : "黄浦区",
		type : "SH"
	}, {
		id : "JA",
		name : "静安区",
		type : "SH"
	}, {
		id : "JD",
		name : "嘉定区",
		type : "SH"
	}, {
		id : "JS",
		name : "金山区",
		type : "SH"
	}, {
		id : "MH",
		name : "闵行区",
		type : "SH"
	}, {
		id : "NH",
		name : "南汇区",
		type : "SH"
	}, {
		id : "PD",
		name : "浦东区",
		type : "SH"
	}, {
		id : "PT",
		name : "普陀区",
		type : "SH"
	}, {
		id : "QP",
		name : "青浦区",
		type : "SH"
	}, {
		id : "SJ",
		name : "松江区",
		type : "SH"
	}, {
		id : "XH",
		name : "徐汇区",
		type : "SH"
	}, {
		id : "YP",
		name : "杨浦区",
		type : "SH"
	}, {
		id : "ZB",
		name : "闸北区",
		type : "SH"
	} ], CodesRenderer.regionRenderer),
	// industry list
	industryList : new Codes([ {
		id : "EDU",
		text : "Education"
	}, {
		id : "IT",
		text : "I.T."
	}, {
		id : "INS",
		text : "Insurance"
	} ]),
	// gender list
	genderList : new Codes([ {
		id : "F",
		text : "Female"
	}, {
		id : "M",
		text : "Male"
	} ])
};
