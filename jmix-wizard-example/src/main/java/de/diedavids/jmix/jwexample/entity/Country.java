package de.diedavids.jmix.jwexample.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Country implements EnumClass<String> {

    AFGHANISTAN("AFGHANISTAN"),
    ALBANIA("ALBANIA"),
    ALGERIA("ALGERIA"),
    ANDORRA("ANDORRA"),
    ANGOLA("ANGOLA"),
    ARGENTINA("ARGENTINA"),
    ARMENIA("ARMENIA"),
    AUSTRALIA("AUSTRALIA"),
    AUSTRIA("AUSTRIA"),
    AZERBAIJAN("AZERBAIJAN"),
    BAHAMAS("BAHAMAS"),
    BAHRAIN("BAHRAIN"),
    BANGLADESH("BANGLADESH"),
    BARBADOS("BARBADOS"),
    BELARUS("BELARUS"),
    BELGIUM("BELGIUM"),
    BELIZE("BELIZE"),
    BENIN("BENIN"),
    BHUTAN("BHUTAN"),
    BOLIVIA("BOLIVIA"),
    BOSNIA_HERZEGOVINA("BOSNIA_HERZEGOVINA"),
    BOTSWANA("BOTSWANA"),
    BRAZIL("BRAZIL"),
    BRUNEI("BRUNEI"),
    BULGARIA("BULGARIA"),
    BURKINA("BURKINA"),
    BURUNDI("BURUNDI"),
    CAMBODIA("CAMBODIA"),
    CAMEROON("CAMEROON"),
    CANADA("CANADA"),
    CAPE_VERDE("CAPE_VERDE"),
    CENTRAL_AFRICAN_REP("CENTRAL_AFRICAN_REP"),
    CHAD("CHAD"),
    CHILE("CHILE"),
    CHINA("CHINA"),
    COLOMBIA("COLOMBIA"),
    COMOROS("COMOROS"),
    CONGO("CONGO"),
    COSTA_RICA("COSTA_RICA"),
    CROATIA("CROATIA"),
    CUBA("CUBA"),
    CYPRUS("CYPRUS"),
    CZECH_REPUBLIC("CZECH_REPUBLIC"),
    DENMARK("DENMARK"),
    DJIBOUTI("DJIBOUTI"),
    DOMINICA("DOMINICA"),
    DOMINICAN_REPUBLIC("DOMINICAN_REPUBLIC"),
    EAST_TIMOR("EAST_TIMOR"),
    ECUADOR("ECUADOR"),
    EGYPT("EGYPT"),
    EL_SALVADOR("EL_SALVADOR"),
    EQUATORIAL_GUINEA("EQUATORIAL_GUINEA"),
    ERITREA("ERITREA"),
    ESTONIA("ESTONIA"),
    ETHIOPIA("ETHIOPIA"),
    FIJI("FIJI"),
    FINLAND("FINLAND"),
    FRANCE("FRANCE"),
    GABON("GABON"),
    GAMBIA("GAMBIA"),
    GEORGIA("GEORGIA"),
    GERMANY("GERMANY"),
    GHANA("GHANA"),
    GREECE("GREECE"),
    GRENADA("GRENADA"),
    GUATEMALA("GUATEMALA"),
    GUINEA("GUINEA"),
    GUYANA("GUYANA"),
    HAITI("HAITI"),
    HONDURAS("HONDURAS"),
    HUNGARY("HUNGARY"),
    ICELAND("ICELAND"),
    INDIA("INDIA"),
    INDONESIA("INDONESIA"),
    IRAN("IRAN"),
    IRAQ("IRAQ"),
    ISRAEL("ISRAEL"),
    ITALY("ITALY"),
    IVORY_COAST("IVORY_COAST"),
    JAMAICA("JAMAICA"),
    JAPAN("JAPAN"),
    JORDAN("JORDAN"),
    KAZAKHSTAN("KAZAKHSTAN"),
    KENYA("KENYA"),
    KIRIBATI("KIRIBATI"),
    KOREA_NORTH("KOREA_NORTH"),
    KOREA_SOUTH("KOREA_SOUTH"),
    KOSOVO("KOSOVO"),
    KUWAIT("KUWAIT"),
    KYRGYZSTAN("KYRGYZSTAN"),
    LAOS("LAOS"),
    LATVIA("LATVIA"),
    LEBANON("LEBANON"),
    LESOTHO("LESOTHO"),
    LIBERIA("LIBERIA"),
    LIBYA("LIBYA"),
    LIECHTENSTEIN("LIECHTENSTEIN"),
    LITHUANIA("LITHUANIA"),
    LUXEMBOURG("LUXEMBOURG"),
    MACEDONIA("MACEDONIA"),
    MADAGASCAR("MADAGASCAR"),
    MALAWI("MALAWI"),
    MALAYSIA("MALAYSIA"),
    MALDIVES("MALDIVES"),
    MALI("MALI"),
    MALTA("MALTA"),
    MARSHALL_ISLANDS("MARSHALL_ISLANDS"),
    MAURITANIA("MAURITANIA"),
    MAURITIUS("MAURITIUS"),
    MEXICO("MEXICO"),
    MICRONESIA("MICRONESIA"),
    MOLDOVA("MOLDOVA"),
    MONACO("MONACO"),
    MONGOLIA("MONGOLIA"),
    MONTENEGRO("MONTENEGRO"),
    MOROCCO("MOROCCO"),
    MOZAMBIQUE("MOZAMBIQUE"),
    NAMIBIA("NAMIBIA"),
    NAURU("NAURU"),
    NEPAL("NEPAL"),
    NETHERLANDS("NETHERLANDS"),
    NEW_ZEALAND("NEW_ZEALAND"),
    NICARAGUA("NICARAGUA"),
    NIGER("NIGER"),
    NIGERIA("NIGERIA"),
    NORWAY("NORWAY"),
    OMAN("OMAN"),
    PAKISTAN("PAKISTAN"),
    PALAU("PALAU"),
    PANAMA("PANAMA"),
    PAPUA_NEW_GUINEA("PAPUA_NEW_GUINEA"),
    PARAGUAY("PARAGUAY"),
    PERU("PERU"),
    PHILIPPINES("PHILIPPINES"),
    POLAND("POLAND"),
    PORTUGAL("PORTUGAL"),
    QATAR("QATAR"),
    ROMANIA("ROMANIA"),
    RUSSIAN_FEDERATION("RUSSIAN_FEDERATION"),
    RWANDA("RWANDA"),
    ST_LUCIA("ST_LUCIA"),
    SAMOA("SAMOA"),
    SAN_MARINO("SAN_MARINO"),
    SAUDI_ARABIA("SAUDI_ARABIA"),
    SENEGAL("SENEGAL"),
    SERBIA("SERBIA"),
    SEYCHELLES("SEYCHELLES"),
    SIERRA_LEONE("SIERRA_LEONE"),
    SINGAPORE("SINGAPORE"),
    SLOVAKIA("SLOVAKIA"),
    SLOVENIA("SLOVENIA"),
    SOLOMON_ISLANDS("SOLOMON_ISLANDS"),
    SOMALIA("SOMALIA"),
    SOUTH_AFRICA("SOUTH_AFRICA"),
    SOUTH_SUDAN("SOUTH_SUDAN"),
    SPAIN("SPAIN"),
    SRI_LANKA("SRI_LANKA"),
    SUDAN("SUDAN"),
    SURINAME("SURINAME"),
    SWAZILAND("SWAZILAND"),
    SWEDEN("SWEDEN"),
    SWITZERLAND("SWITZERLAND"),
    SYRIA("SYRIA"),
    TAIWAN("TAIWAN"),
    TAJIKISTAN("TAJIKISTAN"),
    TANZANIA("TANZANIA"),
    THAILAND("THAILAND"),
    TOGO("TOGO"),
    TONGA("TONGA"),
    TUNISIA("TUNISIA"),
    TURKEY("TURKEY"),
    TURKMENISTAN("TURKMENISTAN"),
    TUVALU("TUVALU"),
    UGANDA("UGANDA"),
    UKRAINE("UKRAINE"),
    UNITED_ARAB_EMIRATES("UNITED_ARAB_EMIRATES"),
    UNITED_KINGDOM("UNITED_KINGDOM"),
    UNITED_STATES("UNITED_STATES"),
    URUGUAY("URUGUAY"),
    UZBEKISTAN("UZBEKISTAN"),
    VANUATU("VANUATU"),
    VATICAN_CITY("VATICAN_CITY"),
    VENEZUELA("VENEZUELA"),
    VIETNAM("VIETNAM"),
    YEMEN("YEMEN"),
    ZAMBIA("ZAMBIA"),
    ZIMBABWE("ZIMBABWE");

    private String id;

    Country(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Country fromId(String id) {
        for (Country at : Country.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}