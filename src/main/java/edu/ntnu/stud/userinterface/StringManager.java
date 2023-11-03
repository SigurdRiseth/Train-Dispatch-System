package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.station.Station;
import edu.ntnu.stud.traindeparture.TrainDeparture;
import java.util.Iterator;

/**
 * Class for managing all the methods creating strings used in the UI.
 */
public class StringManager {

  private Station station;

  /**
   * Constructor that sets the station.
   * @param station The station to be set
   */
  public StringManager(Station station) {
    this.station = station;
  }

  /**
   * Returns a String of the menu options.
   * @return String
   */
  public String options() {
    return String.join("\n",
        "-------------------------------------------",
        "1: Print all upcoming departures",
        "2: Print all upcoming departures to a given destination",
        "3: Print the next departure to a given destination",
        "4: Add a new train departure",
        "5: Set delay for a train departure",
        "6: Set track for a train departure",
        "7: Get train by train number",
        "8: Remove train by train number",
        "9: Set the clock",
        "10: Exit",
        "Please enter a number between 1 and 10:"
    );
  }


  /**
   * Returns a String of all departures yet to depart.
   * @return String
   */
  public String getAllDepartures() {
    StringBuilder result = new StringBuilder();
    result.append("Here is a list of all the trains that are yet to depart:\n");
    result.append("Train number\tLine\tDestination\t\t\tDeparture time\tTrack\n");

    Iterator<TrainDeparture> iterator = station.getTrainDeparturesSorted().iterator();
    while (iterator.hasNext()) {
      TrainDeparture trainDeparture = iterator.next();
      String formattedLine = String.format("%-15s%-5s%-20s%-15s%-10s",
          trainDeparture.getTrainNumber(),
          trainDeparture.getLine(),
          trainDeparture.getDestination(),
          trainDeparture.getDepartureTime(),
          (trainDeparture.getTrack() == -1) ? "-" : String.valueOf(trainDeparture.getTrack())
      );
      result.append(formattedLine).append("\n");
    }

    return String.valueOf(result);
  }

  /**
   * Return all departures to a given destination.
   * The destination can be given in any capitalisation.
   * If no departures are found, a message is returned.
   *
   * @param destination
   * @return String
   */
  public String printAllDeparturesToDestination(String destination) {
    destination = destination.substring(0, 1).toUpperCase()
        + destination.substring(1).toLowerCase();
    Iterator<TrainDeparture> iterator = station.getTrainDeparturesSorted().iterator();
    StringBuilder result = new StringBuilder();
    boolean foundDeparture = false;
    result.append("Here is a list of all the trains that are yet to depart to " + destination
        + ":\n");
    result.append("Train number\tLine\tDestination\t\t\tDeparture time\tTrack\n");
    while (iterator.hasNext()) {
      TrainDeparture trainDeparture = iterator.next();
      if (trainDeparture.getDestination().equals(destination)) {
        String formattedLine = String.format("%-15s%-5s%-20s%-15s%-10s",
            trainDeparture.getTrainNumber(),
            trainDeparture.getLine(),
            trainDeparture.getDestination(),
            trainDeparture.getDepartureTime(),
            (trainDeparture.getTrack() == -1) ? "-" : String.valueOf(trainDeparture.getTrack())
        );
        result.append(formattedLine).append("\n");
        foundDeparture = true;
      }
    }
    if (!foundDeparture) {
      return "No trains to " + destination + " found.";
    }
    return String.valueOf(result);
  }

  /**
   * Returns the next departure to a given destination.
   * The destination can be given in any capitalisation.
   * If no departures are found, a message is returned.
   *
   * @param destination2
   * @return String
   */
  public String getNextDepartureToDestination(String destination2) { // TODO: Dumt å kalle metoden flere ganger istedet for å lagre trainnumber og heller kalle get funksjonen til det spesifikke toget?
    destination2 = destination2.substring(0, 1).toUpperCase() + destination2.substring(1).toLowerCase();
    try {
      return (
          "The next train to " + station.getTrainDepartureByDestination(destination2)
              .getDestination()
              + " departs at " + station.getTrainDepartureByDestination(destination2)
              .getDepartureTime() + " from track " + station.getTrainDepartureByDestination(
              destination2).getTrack());
    } catch (Exception e) {
      return "No train to " + destination2 + " was found.";
    }
  }

}
