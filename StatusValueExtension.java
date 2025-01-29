public final class StatusValueExtension {

    public static String GetStatusString(StatusValue status) {
        switch(status) {
            case Completed:
                return "Completed";
            case Neutral:
               return "Neutral";
            case Dropped:
                return "Dropped";
            default:
                return "";
        }
    }

    public static StatusValue GetStatusValue(String statusAsString) {
        StatusValue statusfinal = StatusValue.Neutral;
        switch(statusAsString) {
            case "Completed":
                statusfinal = StatusValue.Completed;
                break;
            case "Neutral":
                statusfinal = StatusValue.Neutral;
                break;
            case "Dropped":
                statusfinal = StatusValue.Dropped;
                break;
            default:
                System.out.println("Error to dectect the status of the task.\nThe default status Neutral has been assign to your task.");
                break;
        }   
        return statusfinal;
    }
}
