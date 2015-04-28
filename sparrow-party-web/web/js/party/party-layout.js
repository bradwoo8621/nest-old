/**
 * model layout
 */
var AddressModelLayout = {
	type : "list",
	layout : {
		addressId : {
			type : "number"
		},
		partyId : {
			type : "number"
		},
		country : {
			type : "code",
			check : {}
		},
		province : {
			type : "code",
			check : {}
		},
		city : {
			type : "code",
		},
		district : {
			type : "code",
		},
		addressLine1 : {
			type : "text",
			check : {
				length : 100
			}
		},
		addressLine2 : {
			type : "text",
			check : {
				length : 100
			}
		},
		addressLine3 : {
			type : "text",
			check : {
				length : 100
			}
		},
		addressLine4 : {
			type : "text",
			check : {
				length : 100
			}
		},
		addressLine5 : {
			type : "text",
			check : {
				length : 100
			}
		},
		postcode : {
			type : "text",
			check : {
				length : 6,
				minLength : 6
			}
		},
		telephone : {
			type : "text",
			check : {}
		},
		fax : {
			type : "text",
			check : {}
		}
	},
};
var IndividualModelLayout = {
	partyId : {
		type : "number"
	},
	firstName : {
		type : "text",
		check : {
			length : 30,
			required : true,
		}
	},
	middelName : {
		type : "text",
		check : {
			length : 30
		}
	},
	lastName : {
		type : "text",
		check : {
			length : 30,
			required : true,
		}
	},
	idNumber : {
		type : "text",
		check : {
			length : 10,
			required : true
		}
	},
	gender : {
		type : "code",
		check : {
			required : true,
		}
	},
	dateOfBirth : {
		type : "date",
		check : {

		}
	},
	dateOfDeath : {
		type : "date",
		check : {

		}
	},
	bornIn : {
		type : "code",
		check : {

		}
	},
	nationality : {
		type : "code",
		check : {}
	},
	partyCode : {
		type : "text",
		check : {
			lenght : 10,
			required : true
		}
	},
	partyEnabled : {
		type : "boolean",
		dVal : true,
		check : {
			required : true,
		}
	},
	addresses : AddressModelLayout,
};
var OrganizationModelLayout = {
	partyId : {
		type : "number"
	},
	partyName : {
		type : "text",
		check : {
			length : 100,
			required : true,
		}
	},
	registerNumber : {
		type : "text",
		check : {
			length : 10,
			required : true
		}
	},
	registerDate : {
		type : "date",
		check : {

		}
	},
	closeDownDate : {
		type : "date",
		check : {

		}
	},
	registerIn : {
		type : "code",
		check : {

		}
	},
	industry : {
		type : "code",
		check : {}
	},
	partyCode : {
		type : "text",
		check : {
			length : 10,
			required : true
		}
	},
	partyEnabled : {
		type : "boolean",
		dVal : true,
		check : {
			required : true,
		}
	},
	addresses : AddressModelLayout,
};
var RoleModelLayout = {
	roleCode : {
		type : "text",
		check : {
			length : 10,
			required : true
		}
	},
	roleEnabled : {
		type : "boolean",
		dVal : true,
		check : {
			required : true
		}
	}

};
var PartyRoleModelLayoutConfig = {
	EDU : new ModelLayoutProxy($.extend({}, OrganizationModelLayout, RoleModelLayout, {
		headOffice : {
			type : "boolean",
			dVal : false,
			check : {}
		},
		parentBranch : {
			type : "refer",
			check : {}
		},
		artificialPerson : {
			type : "refer",
			check : {}
		}
	})),
	MBR : new ModelLayoutProxy($.extend({}, OrganizationModelLayout, RoleModelLayout, {
		headOffice : {
			type : "boolean",
			dVal : false,
			check : {}
		},
		parentBranch : {
			type : "refer",
			check : {}
		},
	})),
	MDP : new ModelLayoutProxy($.extend({}, OrganizationModelLayout, RoleModelLayout, {
		branch : {
			type : "refer",
			check : {}
		},
		parentDepartment : {
			type : "refer",
			check : {}
		}
	})),
	MEM : new ModelLayoutProxy($.extend({}, IndividualModelLayout, RoleModelLayout, {
		branch : {
			type : "refer",
			check : {}
		},
		department : {
			type : "refer",
			check : {}
		}
	})),
	RDP : new ModelLayoutProxy($.extend({}, OrganizationModelLayout, RoleModelLayout, {
		branch : {
			type : "refer",
			check : {}
		},
		parentDepartment : {
			type : "refer",
			check : {}
		}
	})),
	REP : new ModelLayoutProxy($.extend({}, IndividualModelLayout, RoleModelLayout, {
		branch : {
			type : "refer",
			check : {}
		},
		department : {
			type : "refer",
			check : {}
		}
	})),
	AGO : new ModelLayoutProxy($.extend({}, OrganizationModelLayout, RoleModelLayout)),
	AGI : new ModelLayoutProxy($.extend({}, IndividualModelLayout, RoleModelLayout)),
};

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
	columns : {
		country : {
			title : "Country",
			render : function(data) {
				var code = Codes.countryList.get(data);
				return code == null ? "" : code.name;
			},
			width : 120,
			edit : {
				row : 1,
				col : 1,
				type : "select",
				selectOptions : Codes.countryList,
				needSearchBox : true,
			},
			index : 10
		},
		province : {
			title : "Province",
			render : function(data) {
				var code = Codes.provinceList.get(data);
				return code == null ? "" : code.name;
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
			},
			index : 20
		},
		city : {
			title : "City",
			render : function(data) {
				var code = Codes.cityList.get(data);
				return code == null ? "" : code.name;
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
			},
			index : 30
		},
		district : {
			title : "District",
			render : function(data) {
				var code = Codes.districtList.get(data);
				return code == null ? "" : code.name;
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
			},
			index : 40
		},
		addressLine1 : {
			title : "Line1",
			width : 200,
			edit : {
				row : 2,
				col : 1,
				width : 6,
				type : "text",
			},
			index : 50
		},
		addressLine2 : {
			title : "Line2",
			width : 150,
			edit : {
				row : 2,
				col : 2,
				width : 6,
				type : "text",
			},
			index : 60
		},
		addressLine3 : {
			title : "Line3",
			width : 150,
			edit : {
				row : 3,
				col : 1,
				width : 6,
				type : "text",
			},
			index : 70
		},
		addressLine4 : {
			title : "Line4",
			width : 150,
			edit : {
				row : 3,
				col : 2,
				width : 6,
				type : "text",
			},
			index : 80
		},
		addressLine5 : {
			title : "Line5",
			width : 150,
			edit : {
				row : 4,
				col : 1,
				width : 6,
				type : "text",
			},
			index : 90
		},
		postcode : {
			title : "Postcode",
			width : 100,
			edit : {
				row : 5,
				col : 1,
				width : 4,
				type : "text",
			},
			index : 100,
			sort : "number", // can be function(a, b) or true/false
		},
		telephone : {
			title : "Telephone",
			width : 100,
			edit : {
				row : 5,
				col : 2,
				width : 4,
				type : "text",
			},
			index : 110
		},
		fax : {
			title : "Fax",
			width : 100,
			edit : {
				row : 5,
				col : 3,
				width : 4,
				type : "text",
			},
			index : 120,
			sort : false
		}
	},
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
		searchable : true,
		sortable : true, // can be defined in each column
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
	closeDownDate : {
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
	AGO : new Layout(Organization, Role),
	AGI : new Layout(Individual, Role)
};
