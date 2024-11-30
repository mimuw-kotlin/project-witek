Assumptions:
- On first use, user should click "start the challenge today" button (or similar). This will initialize Timeline with startDate being the actual date.
- From that point going onwards, user will be getting daily notification to check status of resolutions.
- If no daily status is provided, the entry for this day is created with all resolutions set to UNKNOWN (Day.getEmpty()).
- Apart from "start the challenge today", user can choose to "import data from..." (at first, the only supported format is CSV).
- After import, the Timeline should be created with startDate set to the earliest date in data and every subsequent Day set to either values from corresponding record or empty (all UNKNOWN).
