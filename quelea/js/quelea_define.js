/**
 * model define
 */
var ModelDefine = {
    // individual party
    IndividualParty: {
        partyId: null,
        idNumber: null,
        partyCode: null,
        partyTypeCode: 'I',
        enabled: true,
        accounts: null,
        addresses: null,
        relations: null,
        // individual
        firstName: null,
        middleName: null,
        lastName: null,
        alias: null,
        countryCodeOfBirth: null,
        courtesyTitle: null,
        dateOfBirth: null,
        dateOfDeath: null,
        genderCode: null,
        idTypeCode: null,
        maritalCode: null,
        nationalityCode: null,
        occupationCode: null,
        raceCode: null,
        religionCode: null,
        //auditable
        createdBy: null,
        createdDate: null,
        lastModifiedBy: null,
        lastModifiedDate: null,
        versionNumber: null
    },
    // organization party
    OrganizationParty: {
        partyId: null,
        idNumber: null,
        partyCode: null,
        partyTypeCode: 'O',
        enabled: true,
        accounts: null,
        addresses: null,
        relations: null,
        // organization
        dateOfDeregister: null,
        dateOfRegister: null,
        idTypeCode: null,
        legalRepresentative: null,
        legalTypeCode: null,
        organizationName: null,
        // auditable
        createdBy: null,
        createdDate: null,
        lastModifiedBy: null,
        lastModifiedDate: null,
        versionNumber: null
    },
    PartyQueryModel: {
        partyTypeCode: null,
        idNumber: null,
        partyCode: null,
        name: null
    },
    RoleQueryModel: {
        roleTypeCode: null,
        idNumber: null,
        partyCode: null,
        roleCode: null,
        name: null
    }
};
/**
 * layout define. key of cell must be same as key of model
 */
var LayoutDefine = {
    // party common
    PartyCommon: {
        partyCode: {
            label: "Party Code",
            // comp attribute is optional is no sub properties
            comp: {
                type: ComponentConstants.Text // optional, default is ComponentConstants.Text
            },
            pos: {
                col: 1, row: 1, width: 3 // optional is it is 3
            }
        },
        idNumber: {
            label: "Register Number",
            pos: {col: 3, row: 1}
        },
        enabled: {
            label: "Enabled",
            comp: {type: ComponentConstants.Check},
            pos: {col: 4, row: 1}
        },
        addresses: {
            label: "Address",
            comp: {
                type: ComponentConstants.Table,
                //scrollY: 81,
                scrollX: true,
                indexable: true,
                indexFixed: true,
                operationFixed: true,
                editable: true,
                removable: true,
                pageable: true,
                countPerPage: 5,
                // model tempate to create new item
                modelTemplate: {
                    addressId: null,
                    partyId: null,
                    countryCode: "CHN",
                    provinceCode: "SH",
                    cityCode: "SH",
                    districtCode: "YP",
                    postcode: null,
                    enabled: true,
                    addressTypeCode: "1",
                    line1: null,
                    line2: null,
                    line3: null,
                    line4: null,
                    line5: null
                },
                editLayout: {
                    countryCode: {
                        label: "Country",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Regions.Country
                        },
                        pos: {col: 1, row: 1}
                    },
                    provinceCode: {
                        label: "Province",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Regions.Province
                        },
                        pos: {col: 2, row: 1}
                    },
                    cityCode: {
                        label: "City",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Regions.City
                        },
                        pos: {col: 3, row: 1}
                    },
                    districtCode: {
                        label: "District",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Regions.District
                        },
                        pos: {col: 4, row: 1}
                    },
                    postcode: {
                        label: "Postcode",
                        pos: {col: 1, row: 2}
                    },
                    enabled: {
                        label: "Enabled",
                        comp: {type: ComponentConstants.Check},
                        pos: {col: 2, row: 2}
                    },
                    addressTypeCode: {
                        label: "Address Type",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Codes.AddressType
                        },
                        pos: {col: 3, row: 2}
                    },
                    line1: {
                        label: "Line1",
                        pos: {col: 1, row: 3, width: 6}
                    },
                    line2: {
                        label: "Line2",
                        pos: {col: 2, row: 3, width: 6}
                    },
                    line3: {
                        label: "Line3",
                        pos: {col: 1, row: 4, width: 6}
                    },
                    line4: {
                        label: "Line4",
                        pos: {col: 1, row: 4, width: 6}
                    },
                    line5: {
                        label: "Line5",
                        pos: {col: 1, row: 5, width: 6}
                    }
                },
                columns: new TableColumnLayout([{
                    title: "Address Type",
                    width: 150,
                    render: function (data) {
                        return Codes.AddressType.getText(data.addressTypeCode);
                    }
                    /*
                     sort: // optional
                     1: false: disable sort;
                     2: function(a, b): sorter, a and b is data object of row;
                     3: "number": sort as number
                     */
                }, {
                    title: "Country",
                    width: 200,
                    render: function (data) {
                        return Regions.Country.getText(data.countryCode);
                    }
                }, {
                    title: "Province",
                    width: 100,
                    render: function (data) {
                        return Regions.Province.getText(data.provinceCode);
                    }
                }, {
                    title: "City",
                    width: 100,
                    render: function (data) {
                        return Regions.City.getText(data.cityCode);
                    }
                }, {
                    title: "District",
                    width: 100,
                    render: function (data) {
                        return Regions.District.getText(data.districtCode);
                    }
                }, {
                    title: "Postcode",
                    data: "postcode",
                    width: 100
                }, {
                    title: "Address Line",
                    width: 500,
                    render: function (data) {
                        var lines = [data.line1, data.line2, data.line3, data.line4, data.line5];
                        return lines.filter(function (item) {
                            return item != null;
                        }).join(",");
                    }
                }, {
                    title: "Enabled",
                    data: "enabled",
                    width: 100
                }])
            },
            pos: {col: 1, row: 101, width: 12}
        },
        accounts: {
            label: "Account",
            comp: {
                type: ComponentConstants.Table,
                scrollX: true,
                indexable: true,
                indexFixed: true,
                operationFixed: true,
                editable: true,
                removable: true,
                pageable: true,
                countPerPage: 5,
                // model tempate to create new item
                modelTemplate: {
                    accountId: null,
                    partyId: null,
                    accountNumber: null,
                    bankCode: null,
                    accountTypeCode: null,
                    creditCard: false,
                    creditCardTypeCode: null,
                    cardSecurityNumber: null,
                    expiryDate: null,
                    holderName: null,
                    valid: null
                },
                editLayout: {
                    accountNumber: {
                        label: "Account Number",
                        pos: {col: 1, row: 1}
                    },
                    bankCode: {
                        label: "Bank",
                        comp: {
                            type: ComponentConstants.Text
                        },
                        pos: {col: 2, row: 1}
                    },
                    accountTypeCode: {
                        label: "Account Type",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Codes.AccountType,
                            minimumResultsForSearch: Infinity
                        },
                        pos: {col: 3, row: 1}
                    },
                    creditCard: {
                        label: "Is Credit Card",
                        comp: {
                            type: ComponentConstants.Check
                        },
                        pos: {col: 1, row: 2}
                    },
                    creditCardTypeCode: {
                        label: "Credit Card Type",
                        comp: {
                            type: ComponentConstants.Select,
                            data: Codes.CreditCardType,
                            minimumResultsForSearch: Infinity
                        },
                        pos: {col: 2, row: 2}
                    },
                    cardSecurityNumber: {
                        label: "Security Code",
                        pos: {col: 3, row: 2}
                    },
                    expiryDate: {
                        label: "Expiry Date",
                        comp: {
                            type: ComponentConstants.Date
                        },
                        pos: {col: 4, row: 2}
                    },
                    holderName: {
                        label: "Holder Name",
                        pos: {col: 1, row: 3}
                    },
                    valid: {
                        label: "Is Valid",
                        comp: {
                            type: ComponentConstants.Check
                        },
                        pos: {col: 2, row: 3}
                    }
                },
                columns: new TableColumnLayout([{
                    title: "Account Number",
                    width: 200,
                    data: "accountNumber"
                }, {
                    title: "Bank",
                    width: 200,
                    data: "bankCode"
                }, {
                    title: "Account Type",
                    width: 150,
                    render: function (data) {
                        return Codes.AccountType.getText(data.accountTypeCode);
                    }
                }, {
                    title: "Is Credit Card",
                    width: 150,
                    data: "creditCard"
                }, {
                    title: "Credit Card Type",
                    width: 150,
                    render: function (data) {
                        return Codes.CreditCardType.getText(data.creditCardTypeCode);
                    }
                }, {
                    title: "Security Code",
                    data: "cardSecurityNumber",
                    width: 150
                }, {
                    title: "Expiry Date",
                    width: 150,
                    data: "expiryDate"
                }, {
                    title: "Holder Name",
                    width: 200,
                    data: "holderName"
                }, {
                    title: "Enabled",
                    data: "enabled",
                    width: 100
                }])
            },
            pos: {col: 1, row: 201, width: 12}
        }
    },
    // organization party
    OrganizationParty: {
        idTypeCode: {
            label: "Type of Registration",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.OrganizationIdType,
                minimumResultsForSearch: Infinity
            },
            pos: {col: 2, row: 1}
        },
        organizationName: {
            label: "Name",
            pos: {col: 1, row: 11, width: 6}
        },
        legalTypeCode: {
            label: "Legal Type",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.LegalType
            },
            pos: {col: 2, row: 11}
        },
        legalRepresentative: {
            label: "Legal Representative",
            pos: {col: 3, row: 11}
        },
        dateOfRegister: {
            label: "Date of Registration",
            comp: {
                type: ComponentConstants.Date,
                // optional, default is "YYYY/MM/DD"
                // all other options references to bootstrap-datetimepicker
                format: "YYYY-MM-DD"
                // optional, only needs to set if format of display and server side is different
                /*valueFormat: "DD/MM/YYYY",*/
            },
            pos: {col: 1, row: 21}
        },
        dateOfDeregister: {
            label: "Date of Deregistration",
            comp: {type: ComponentConstants.Date},
            pos: {col: 2, row: 21}
        }
    },
    IndividualParty: {
        idTypeCode: {
            label: "Type of Registration",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.IndividualIdType,
                minimumResultsForSearch: Infinity
            },
            pos: {col: 2, row: 1}
        },
        firstName: {
            label: "First Name",
            pos: {col: 1, row: 11}
        },
        middleName: {
            label: "Middle Name",
            pos: {col: 2, row: 11}
        },
        lastName: {
            label: "Last Name",
            pos: {col: 3, row: 11}
        },
        alias: {
            label: "Alias Name",
            pos: {col: 4, row: 11}
        },
        genderCode: {
            label: "Gender",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.Gender,
                minimumResultsForSearch: Infinity
            },
            pos: {col: 1, row: 21}
        },
        dateOfBirth: {
            label: "Date Of Birth",
            comp: {type: ComponentConstants.Date},
            pos: {col: 2, row: 21}
        },
        dateOfDeath: {
            label: "Date Of Death",
            comp: {type: ComponentConstants.Date},
            pos: {col: 3, row: 21}
        },
        countryCodeOfBirth: {
            label: "Country Of Birth",
            comp: {
                type: ComponentConstants.Select,
                data: Regions.Country
            },
            pos: {col: 4, row: 21}
        },
        courtesyTitle: {
            label: "Courtesy Title",
            pos: {col: 1, row: 31}
        },
        maritalCode: {
            label: "Marital",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.Marital,
                minimumResultsForSearch: Infinity
            },
            pos: {col: 2, row: 31}
        },
        nationalityCode: {
            label: "Nationality",
            comp: {
                type: ComponentConstants.Select,
                data: Regions.Country
            },
            pos: {col: 3, row: 31}
        },
        occupationCode: {
            label: "Occupation",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.Occupation
            },
            pos: {col: 4, row: 31}
        },
        raceCode: {
            label: "Race",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.Race,
                minimumResultsForSearch: Infinity
            },
            pos: {col: 1, row: 41}
        },
        religionCode: {
            label: "Religion",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.Religion
            },
            pos: {col: 2, row: 41}
        }
    },
    // query party layout
    PartyQueryLayout: {
        partyTypeCode: {
            label: "Party Type",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.PartyType,
                minimumResultsForSearch: Infinity
            },
            pos: {col: 1, row: 1}
        },
        partyCode: {
            label: "Party Code",
            pos: {col: 2, row: 1}
        },
        idNumber: {
            label: "ID Number",
            pos: {col: 3, row: 1}
        },
        name: {
            label: "Party Name",
            pos: {col: 4, row: 1}
        }
    },
    // quer role layout
    RoleQueryLayout: {
        roleTypeCode: {
            label: "Role Type",
            comp: {
                type: ComponentConstants.Select,
                data: Codes.RoleType
            },
            pos: {col: 1, row: 1, width: 4}
        },
        partyCode: {
            label: "Party Code",
            pos: {col: 2, row: 1, width: 4}
        },
        roleCode: {
            label: "Role Code",
            pos: {col: 3, row: 1, width: 4}
        },
        idNumber: {
            label: "ID Number",
            pos: {col: 1, row: 2, width: 4}
        },
        name: {
            label: "Party Name",
            pos: {col: 2, row: 2, width: 4}
        }
    }
};
/**
 * validator define
 */
var ValidatorDefine = {
    OrganizationPartyValidator: {
        partyCode: {required: true, maxlength: 10},
        idTypeCode: {required: true},
        idNumber: {required: true, length: 10},
        organizationName: {required: true, maxlength: 100},
        legalRepresentative: {maxlength: 30},
        dateOfRegister: {
            required: true,
            before: {
                rule: ["now", "dateOfDeregister"],
                format: "YYYY/MM/DD", // optional, default is YYYY/MM/DD,
                label: ["Now", "\"Date of Deregister\""] // optional, also an array if exists, with same length of rule property
            }
        },
        dateOfDeregister: {
            after: {
                rule: "dateOfRegister",
                label: "\"Date Of Register\""
            }
            /* after : "dateOfRegister" */ // sugar, also support array and available for "before"
        },
        addresses: {
            minsize: 1,
            // "table" is a pre-defined key for validate detail data
            table: {
                addressTypeCode: {required: true},
                line1: {required: true, maxlength: 50},
                line2: {maxlength: 50},
                line3: {maxlength: 50},
                line4: {maxlength: 50},
                line5: {maxlength: 50},
                postcode: {maxlength: 6}
            }
        }
    },
    IndividualPartyValidator: {
        dateOfBirth: {
            requried: true,
            before: {
                rule: ["now", "dateOfDeath"],
                format: "YYYY/MM/DD",
                label: ["Now", "\"Date Of Death\""]
            }
        },
        dateOfDeath: {
            after: {
                rule: "dateOfBirth",
                label: "\"Date Of Birth\""
            }
        },
        firstName: {required: true, maxlength: 30},
        middleName: {maxlength: 30},
        lastName: {required: true, maxlength: 30},
        genderCode: {required: true},
        idNumber: {required: true, length: 18},
        idTypeCode: {required: true},
        partyCode: {required: true, maxlength: 10}
    },
    RoleQueryValidator: {
        roleTypeCode: {
            required: true
        },
        idNumber: {
            _check: function (model) {
                var isIdBlank = model.getIdNumber() == null || model.getIdNumber().isBlank();
                var isNameBlank = model.getName() == null || model.getName().isBlank();
                return isIdBlank && isNameBlank ? "請在ID Number和Party Name中至少填寫一個欄位." : true;
            }
        }
    }
};
