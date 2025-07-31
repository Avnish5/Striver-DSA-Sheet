package DAY_16_String_Part_2;

public class Compare_version_numbers {

    /**
     * 1. Using Split
     *
     * Time Complexity: O(max(n, m))
     *   - Where n = number of segments in version1, m = number of segments in version2
     *   - We iterate through all segments of the longer version string.
     *
     * Space Complexity: O(n + m)
     *   - Due to the space used by the split arrays fArr and sArr.
     */
    public  int compareVersion(String version1, String version2) {
        // Split both version strings by '.' to get their segments
        String[] fArr = version1.split("\\.");
        String[] sArr = version2.split("\\.");

        // Determine the max length of the two arrays
        int maxLength = Math.max(fArr.length, sArr.length);

        // Compare each segment
        for (int i = 0; i < maxLength; i++) {
            // Parse current segment or default to 0 if one version has fewer segments
            int a = i < fArr.length ? Integer.parseInt(fArr[i]) : 0;
            int b = i < sArr.length ? Integer.parseInt(sArr[i]) : 0;

            // Compare the two integer values
            if (a < b) return -1;
            if (a > b) return 1;
        }

        // All segments are equal
        return 0;
    }

}
