package com.triceratops.dinocityserver.models;

import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;

import java.sql.Array;
import java.util.*;

public class EnclosureTypeResponse {
    private SizeType[] types;
    private SecurityLevel[] securityLevels;

    public EnclosureTypeResponse(SizeType[] types, SecurityLevel[] securityLevels) {
        this.types = types;
        this.securityLevels = securityLevels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnclosureTypeResponse that = (EnclosureTypeResponse) o;
        return Arrays.equals(types, that.types) &&
                Arrays.equals(securityLevels, that.securityLevels);
    }
}
