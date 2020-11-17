import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Road} from './road'

@Entity("t_road_level",'tunnel')
export class RoadLevel extends BaseEntity{
	@Id()
	@Column({
		name:'road_level_id',
		type:'int',
		nullable:false
	})
	private roadLevelId:number;

	@Column({
		name:'road_level_name',
		type:'string',
		nullable:true
	})
	private roadLevelName:string;

	@OneToMany({entity:'Road',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'roadLevel',eager:false})
	private roads:Array<Road>;

	public getRoadLevelId():number{
		return this.roadLevelId;
	}
	public setRoadLevelId(value:number){
		this.roadLevelId = value;
	}

	public getRoadLevelName():string{
		return this.roadLevelName;
	}
	public setRoadLevelName(value:string){
		this.roadLevelName = value;
	}

	public getRoads():Array<Road>{
		return this.roads;
	}
	public setRoads(value:Array<Road>){
		this.roads = value;
	}

}