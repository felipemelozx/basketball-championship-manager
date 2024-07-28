package com.felipemelozx.championship_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;

import java.util.Set;


@Entity
@Table(name = "tb_teams")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "team_id")
  private Long id;
  private String name;


  @OneToMany(mappedBy = "team")
  private Set<User> players;

  public Team(Long id, String name, Set<User> players) {
    this.id = id;
    this.name = name;
    this.players = players;
  }

  public Team() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<User> getPlayers() {
    return players;
  }

  public void setPlayers(Set<User> players) {
    this.players = players;
  }
}
