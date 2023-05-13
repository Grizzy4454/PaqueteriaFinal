import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrackingSystem {
    private List<Package> packages;

    public TrackingSystem() {
        packages = new ArrayList<>();
    }

    public void addPackage(Package pkg) {
        if (!packages.contains(pkg)) {
            packages.add(pkg);
        }
    }

    public boolean removePackage(String trackingNumber) {
        for (Package pkg : packages) {
            if (pkg.getTrackingNumber().equals(trackingNumber)) {
                packages.remove(pkg);
                return true;
            }
        }
        return false;
    }

    public Package searchByRecipientAddress(String recipientAddress) {
        for (Package pkg : packages) {
            if (pkg.getRecipientAddress().toString().equals(recipientAddress)) {
                return pkg;
            }
        }
        return null;
    }

    public Package searchByTrackingNumber(String trackingNumber) {
        Collections.sort(packages, (pkg1, pkg2) -> pkg1.getTrackingNumber().compareTo(pkg2.getTrackingNumber()));
        int index = Collections.binarySearch(packages, new Package(trackingNumber, null, null, null), (pkg1, pkg2) -> pkg1.getTrackingNumber().compareTo(pkg2.getTrackingNumber()));
        if (index >= 0) {
            return packages.get(index);
        }
        return null;
    }

    public List<Package> searchByCity(String city) {
        List<Package> result = new ArrayList<>();
        for (Package pkg : packages) {
            if (pkg.getRecipientAddress().getCity().equals(city)) {
                result.add(pkg);
            }
        }
        return result;
    }


}