import static org.junit.Assert.assertEquals;

import bean.Bank;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestCompare {


    /**
     * Assert that they are equal
     */
    @Test
    public void noDiffBanks(){
        Map<String, Bank> wdBanks;
        Map<String, Bank> fileBanks;

        wdBanks = new HashMap<String, Bank>();
        fileBanks = new HashMap<String, Bank>();
        wdBanks.put("100", new Bank("ICBC", "300", "100 Street", "Toronto"));
        wdBanks.put("200", new Bank("Bank Toronto", "400", "400 Street", "Toronto"));
        wdBanks.put("300", new Bank("Bank Toronto", "500", "500 Street", "Toronto"));

        fileBanks.put("100", new Bank("ICBC", "300", "100 Street", "Toronto"));
        fileBanks.put("200", new Bank("Bank Toronto", "400", "400 Street", "Toronto"));
        fileBanks.put("300", new Bank("Bank Toronto", "500", "500 Street", "Toronto"));

        MapDifference<String, Bank> diff = Maps.difference(wdBanks, fileBanks);
        assertEquals(true, diff.areEqual());
    }

    /**
     * Change a value in the list to assert that one of the records are not equal
     */
    @Test
    public void diffBanks(){
        Map<String, Bank> wdBanks;
        Map<String, Bank> fileBanks;

        wdBanks = new HashMap<String, Bank>();
        fileBanks = new HashMap<String, Bank>();
        wdBanks.put("100", new Bank("ICBC", "300", "100 Street", "Toronto"));
        wdBanks.put("200", new Bank("Bank Toronto", "400", "400 Street", "Toronto"));
        wdBanks.put("300", new Bank("Bank Toronto", "500", "500 Street", "Toronto"));

        fileBanks.put("100", new Bank("ICBC", "300", "100 Street", "Toronto"));
        fileBanks.put("200", new Bank("Bank Toronto", "400", "400 Street", "Toronto"));
        fileBanks.put("300", new Bank("Bank Toronto", "500", "500 Street", "Toronto"));


        fileBanks.put("100", new Bank("ICBXXXX", "300", "100 Street", "Toronto"));
        MapDifference<String, Bank> diff = Maps.difference(wdBanks, fileBanks);

        assertEquals(false, diff.areEqual());

        System.out.println (diff);
        System.out.println("wdBanks entries: " + diff.entriesOnlyOnLeft());
        System.out.println("fileBanks entries: " + diff.entriesOnlyOnRight());
        Map <String, ValueDifference<Bank>> banksDiffering = diff.entriesDiffering();
    }



    @Test
    public void diffBanksMissingInWd(){
        Map<String, Bank> wdBanks;
        Map<String, Bank> fileBanks;
        wdBanks = new HashMap<String, Bank>();
        fileBanks = new HashMap<String, Bank>();
        wdBanks.put("100", new Bank("ICBC", "300", "100 Street", "Toronto"));
        wdBanks.put("200", new Bank("Bank Toronto", "400", "400 Street", "Toronto"));
        wdBanks.put("300", new Bank("Bank Toronto", "500", "500 Street", "Toronto"));

        fileBanks.put("100", new Bank("ICBC", "300", "100 Street", "Toronto"));
        fileBanks.put("200", new Bank("Bank Toronto", "400", "400 Street", "Toronto"));
        fileBanks.put("300", new Bank("Bank Toronto", "500", "500 Street", "Toronto"));

        fileBanks.put("1000", new Bank("INFILEBANK", "600", "100 Street", "Toronto"));

        MapDifference<String, Bank> diff = Maps.difference(wdBanks, fileBanks);
        System.out.println (diff);
        System.out.println("ON LEFT: " + diff.entriesOnlyOnLeft());
        System.out.println("ON RIGHT: " + diff.entriesOnlyOnRight());
        assertEquals(1, diff.entriesOnlyOnRight().size());
        assertEquals(0, diff.entriesOnlyOnLeft().size());
    }
}
