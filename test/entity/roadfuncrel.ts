import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Road} from './road'
import {FuncCompany} from './funccompany'

@Entity("t_road_func_rel",'tunnel')
export class RoadFuncRel extends BaseEntity{
	@Id()
	@Column({
		name:'road_func_rel_id',
		type:'int',
		nullable:false
	})
	private roadFuncRelId:number;

	@ManyToOne({entity:'Road',eager:false})
	@JoinColumn({name:'road_id',refName:'road_id'})
	private road:Road;

	@ManyToOne({entity:'FuncCompany',eager:false})
	@JoinColumn({name:'func_company_id',refName:'func_company_id'})
	private funcCompany:FuncCompany;

	public getRoadFuncRelId():number{
		return this.roadFuncRelId;
	}
	public setRoadFuncRelId(value:number){
		this.roadFuncRelId = value;
	}

	public getRoad():Road{
		return this.road;
	}
	public setRoad(value:Road){
		this.road = value;
	}

	public getFuncCompany():FuncCompany{
		return this.funcCompany;
	}
	public setFuncCompany(value:FuncCompany){
		this.funcCompany = value;
	}

}