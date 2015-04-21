/**
 * party layout
 */
// row 11
var Addresses = new TableLayout({
	label : "Address",
	type : "table",
	cell : {
		row : 11,
		col : 1,
		width : 12
	},
	columns : [ {
		title : "Country",
		data : "country",
		render : function(data) {
			return  Codes.countryList.get(data).name;
		},
		width : 120,
		edit : {
			row : 1,
			col : 1,
			type : "select",
			selectOptions : Codes.countryList,
			needSearchBox : true,
		}
	}, {
		title : "Province",
		data : "province",
		render : function(data) {
			return Codes.provinceList.get(data).name;
		},
		width : 100,
		edit : {
			row : 1,
			col : 2,
			type : "select",
			selectOptions : Codes.provinceList,
			needSearchBox : true,
			parent : {
				prop : "country",
				filter : "type"
			}
		}
	}, {
		title : "City",
		data : "city",
		render : function(data) {
			return Codes.cityList.get(data).name;
		},
		width : 100,
		edit : {
			row : 1,
			col : 3,
			type : "select",
			selectOptions : Codes.cityList,
			needSearchBox : true,
			parent : {
				prop : "province",
				filter : "type"
			}
		}
	}, {
		title : "District",
		data : "district",
		render : function(data) {
			return Codes.districtList.get(data).name;
		},
		width : 100,
		edit : {
			row : 1,
			col : 4,
			type : "select",
			selectOptions : Codes.districtList,
			needSearchBox : true,
			parent : {
				prop : "city",
				filter : "type"
			}
		}
	}, {
		title : "Line1",
		data : "addressLine1",
		width : 200,
		edit : {
			row : 2,
			col : 1,
			width : 6,
			type : "text",
		}
	}, {
		title : "Line2",
		data : "addressLine2",
		width : 150,
		edit : {
			row : 2,
			col : 2,
			width : 6,
			type : "text",
		}
	}, {
		title : "Line3",
		data : "addressLine3",
		width : 150,
		edit : {
			row : 3,
			col : 1,
			width : 6,
			type : "text",
		}
	}, {
		title : "Line4",
		data : "addressLine4",
		width : 150,
		edit : {
			row : 3,
			col : 2,
			width : 6,
			type : "text",
		}
	}, {
		title : "Line5",
		data : "addressLine5",
		width : 150,
		edit : {
			row : 4,
			col : 1,
			width : 6,
			type : "text",
		}
	}, {
		title : "Postcode",
		data : "postcode",
		width : 100,
		edit : {
			row : 5,
			col : 1,
			width : 4,
			type : "text",
		}
	}, {
		title : "Telephone",
		data : "telephone",
		width : 100,
		edit : {
			row : 5,
			col : 2,
			width : 4,
			type : "text",
		}
	}, {
		title : "Fax",
		data : "fax",
		width : 100,
		edit : {
			row : 5,
			col : 3,
			width : 4,
			type : "text",
		}
	} ],
	layout : {
		scrollY : 200,
		scrollX : true,
		addable : true,
		removable : true,
		editable : true,
		indexable : true,
		// fixedLeftColumns : 1,
		// fixedRightColumns: 1,
		pagable : true,
	}
});
// row 1 - 3
var Organization = {
	registerNumber : {
		label : "Register Number",
		type : "text",
		cell : {
			row : 1,
			col : 1,
		}
	},
	partyCode : {
		label : "Party Code",
		type : "text",
		cell : {
			row : 1,
			col : 2,
		}
	},
	partyEnabled : {
		label : "Party Enabled",
		type : "check",
		cell : {
			row : 1,
			col : 2,
		}
	},
	partyName : {
		label : "Name",
		type : "text",
		cell : {
			row : 2,
			col : 1,
			width : 6
		}
	},
	registerDate : {
		label : "Register Date",
		type : "date",
		cell : {
			row : 3,
			col : 1,
		}
	},
	closeDowndate : {
		label : "End Date",
		type : "date",
		cell : {
			row : 3,
			col : 2,
		}
	},
	registerIn : {
		label : "Register In",
		type : "select",
		selectOptions : Codes.countryList,
		needSearchBox : true,
		cell : {
			row : 3,
			col : 3,
		}
	},
	industry : {
		label : "Industry",
		type : "select",
		selectOptions : Codes.industryList,
		needSearchBox : true,
		cell : {
			row : 3,
			col : 4,
		}
	},
	addresses : Addresses
};
// row 1 - 3
var Individual = {
	idNumber : {
		label : "ID Number",
		type : "text",
		cell : {
			row : 1,
			col : 1,
		}
	},
	partyCode : {
		label : "Party Code",
		type : "text",
		cell : {
			row : 1,
			col : 2,
		}
	},
	partyEnabled : {
		id : "partyEnabled",
		label : "Party Enabled",
		type : "check",
		cell : {
			row : 1,
			col : 3,
		}
	},
	firstName : {
		label : "First Name",
		type : "text",
		cell : {
			row : 2,
			col : 1,
		}
	},
	middleName : {
		label : "Middle Name",
		type : "text",
		cell : {
			row : 2,
			col : 2,
		}
	},
	lastName : {
		label : "Last Name",
		type : "text",
		cell : {
			row : 2,
			col : 3,
		}
	},
	gender : {
		label : "Gender",
		type : "select",
		selectOptions : Codes.genderList,
		needSearchBox : false,
		cell : {
			row : 2,
			col : 4,
		}
	},
	dateOfBirth : {
		label : "Date Of Birth",
		type : "date",
		cell : {
			row : 3,
			col : 1,
		}
	},
	dateOfDeath : {
		label : "Date Of Death",
		type : "date",
		cell : {
			row : 3,
			col : 2,
		}
	},
	bornIn : {
		label : "Born In",
		type : "select",
		selectOptions : Codes.countryList,
		needSearchBox : true,
		cell : {
			row : 3,
			col : 3,
		}
	},
	nationality : {
		label : "Nationality",
		type : "select",
		selectOptions : Codes.countryList,
		needSearchBox : true,
		cell : {
			row : 3,
			col : 4,
		}
	},
	addresses : Addresses
};
// row 21
var Role = {
	roleCode : {
		label : "Role Code",
		type : "text",
		cell : {
			row : 21,
			col : 1,
		}
	},
	roleEnabled : {
		label : "Role Enabled",
		type : "check",
		cell : {
			row : 21,
			col : 2,
		}
	}
};
var PartyRoleConfig = {
	EDU : new Layout(Organization, Role, {
		headOffice : {
			label : "Is Head Office",
			type : "check",
			cell : {
				row : 21,
				col : 3,
			}
		},
		parentBranch : {
			label : "Parent Branch",
			type : "search",
			cell : {
				row : 31,
				col : 1,
				width : 6
			}
		},
		artificialPerson : {
			label : "Artificial Person",
			type : "search",
			cell : {
				row : 31,
				col : 2,
				width : 6
			}
		}
	}),
	MBR : new Layout(Organization, Role, {
		headOffice : {
			label : "Is Head Office",
			type : "check",
			cell : {
				row : 21,
				col : 3,
			}
		},
		parentBranch : {
			label : "Parent Branch",
			type : "select",
			cell : {
				row : 21,
				col : 4,
			}
		}
	}),
	MDP : new Layout(Organization, Role, {
		branch : {
			label : "Branch",
			type : "select",
			cell : {
				row : 21,
				col : 3,
			}
		},
		parentDepartment : {
			label : "Parent Department",
			type : "select",
			cell : {
				row : 21,
				col : 4,
			}
		}
	}),
	MEM : new Layout(Individual, Role, {
		branch : {
			label : "Branch",
			type : "select",
			cell : {
				row : 21,
				col : 3,
			}
		},
		department : {
			label : "Department",
			type : "select",
			cell : {
				row : 21,
				col : 4,
			}
		}
	}),
	RDP : new Layout(Organization, Role, {
		branch : {
			label : "Branch",
			type : "search",
			cell : {
				row : 21,
				col : 3,
			}
		},
		parentDepartment : {
			label : "Parent Department",
			type : "select",
			cell : {
				row : 21,
				col : 4,
			}
		}
	}),
	REP : new Layout(Individual, Role, {
		branch : {
			label : "Branch",
			type : "search",
			cell : {
				row : 31,
				col : 1,
				width : 6
			}
		},
		department : {
			label : "Department",
			type : "select",
			cell : {
				row : 31,
				col : 2,
			}
		}
	}),
	AGT_O : new Layout(Organization, Role),
	AGT_I : new Layout(Individual, Role)
}
