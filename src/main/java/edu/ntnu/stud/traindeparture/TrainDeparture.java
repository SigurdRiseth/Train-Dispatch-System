package edu.ntnu.stud.traindeparture;

import edu.ntnu.stud.station.Station;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Class for the train departures.
 * <p>Will store information such as train number, line, departure time, delay,
 * destination and what track it will depart from.</p>
 *
 * @author Sigurd Riseth
 * @version 0.0.1
 * @since 14.10.2023
 */
public class TrainDeparture {

  private int track;
  private int trainNumber;
  private String line;
  private String destination;
  private LocalTime departureTime; // use a Clock class to store departureTime and delay.
  private LocalTime delay;

  /**
   * Constructor that sets all parameters of the class except delay.
   *
   * @param track         track the train will depart from
   * @param trainNumber   unique train ID
   * @param line          the line the train is driving
   * @param destination   the ends destination of the train
   * @param departureTime the time the train is set to depart
   */
  public TrainDeparture(int track, int trainNumber, String line, String destination,
      LocalTime departureTime, Station station) {
    this.setTrack(track);
    this.setTrainNumber(trainNumber, station);
    this.setLine(line);
    this.setDestination(destination);
    this.setDepartureTime(departureTime, station);
    this.delay = LocalTime.of(0, 0);
  }

  /**
   * Sets the track.
   */
  public void setTrack(int track) {
    this.track = track;
  }

  /**
   * Sets the train number.
   * <p>Also checks if the trainNumber is above 0 and not already stored in Stations HashMap.</p>
   */
  public void setTrainNumber(int trainNumber, Station station) {
    if ((trainNumber > 0) && !station.trainExists(trainNumber)) {
      this.trainNumber = trainNumber;
    } else {
      this.trainNumber = -1;
    }
  }

  /**
   * Sets the line.
   * Can also be set to null.
   *
   * @param line line to be set
   */
  public void setLine(String line) {
    this.line = line;
  }

  /**
   * Sets the destination.
   * If destination is null, it will be set to "Invalid destination".
   *
   * @param destination destination to be set
   */
  public void setDestination(String destination) {
    this.destination = Objects.requireNonNullElse(destination, "Invalid destination");
  }

  /**
   * Sets the departure time.
   * Can only be set to a time after the current time.
   *
   * @param departureTime departure time to be set
   */
  public void setDepartureTime(LocalTime departureTime, Station station) {
    if (departureTime.isAfter(station.getClock())) {
      this.departureTime = departureTime;
    }
  }

  /**
   * Sets the delay.
   *
   * @param delay delay to be set
   */
  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  /**
   * Retrieves the track number as an integer.
   *
   * @return An integer representing the track number.
   */

  public int getTrack() {
    return track;
  }


  /**
   * Retrieves the train number as an integer.
   *
   * @return An integer representing the train number.
   */
  public int getTrainNumber() {
    return trainNumber;
  }


  /**
   * Retrieves the line information as a String.
   *
   * @return A String containing information about the line.
   */
  public String getLine() {
    return line;
  }


  /**
   * Retrieves the destination of this entity as a String.
   *
   * @return A String representing the destination.
   */
  public String getDestination() {
    return destination;
  }


  /**
   * Retrieves the departure time (delay included) as a LocalTime object.
   *
   * @return The LocalTime object representing the departure time.
   */
  public LocalTime getDepartureTime() {
    return this.departureTime.plusHours(this.delay.getHour())
        .plusMinutes(this.delay.getMinute());
  }

  /**
   * Retrieves the delay represented as a LocalTime.
   *
   * @return The LocalTime object representing the delay.
   */
  public LocalTime getDelay() {
    return delay;
  }
}
